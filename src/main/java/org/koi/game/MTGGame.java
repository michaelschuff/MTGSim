package org.koi.game;

import org.koi.event.EventManager;
import org.koi.event.spell.ResolveStackOnce;
import org.koi.event.turn.*;
import org.koi.event.turn.beginning.*;
import org.koi.event.turn.combat.*;
import org.koi.event.turn.end.CleanupStepBeginEvent;
import org.koi.event.turn.end.CleanupStepEndEvent;
import org.koi.event.turn.end.EndPhaseBeginEvent;
import org.koi.event.turn.end.EndPhaseEndEvent;
import org.koi.event.turn.main.MainPostCombatPhaseBeginEvent;
import org.koi.event.turn.main.MainPostCombatPhaseEndEvent;
import org.koi.event.turn.main.MainPreCombatPhaseBeginEvent;
import org.koi.event.turn.main.MainPreCombatPhaseEndEvent;
import org.koi.event.zonechange.DrawCardEvent;
import org.koi.event.zonechange.action.ActivateAbilityEvent;
import org.koi.event.zonechange.action.PlayLandEvent;
import org.koi.gameobject.ability.ActivatedAbility;
import org.koi.gameobject.card.Card;
import org.koi.gameobject.card.OracleCard;
import org.koi.turn.PhaseType;
import org.koi.turn.TurnController;
import org.koi.turnbasedaction.*;
import org.koi.util.GameObjectOrPlayer;
import org.koi.util.Player;
import org.koi.util.Variant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.koi.cards.CardLoader.loadCard;

public class MTGGame {
    private TurnController turnController;
    public GameData data;
    public EventManager eventManager;


    private final Scanner scanner = new Scanner(System.in);

    public MTGGame(Variant variant) {
        switch(variant) {
            case Standard:
                this.turnController = new TurnController(2);
                break;
            case Commander: default:
                this.turnController = new TurnController(4);
                break;
        }
        this.data = new GameData(this);
        this.eventManager = new EventManager(this);
    }
    public void loadDecks(List<List<String>> decklists) {
        List<List<OracleCard>> decks = new ArrayList<>();
        for (List<String> deck : decklists) {
            decks.add(new ArrayList<>());
            for (String card_name : deck) {
                decks.get(decks.size() - 1).add(loadCard(this, card_name));
            }
        }
        this.data.setLibraries(decks);
        this.initTurnBasedActions();
    }

    public void start(List<List<String>> decklists) {
        loadDecks(decklists);
        // TODO: make turnController into an Event listener
        // TODO: mulligans and pregame actions
        for (Player p : data.players) {
            for (int i = 0; i < 7; i++) {
                eventManager.addEvent(new DrawCardEvent(this, p));
            }
        }
        eventManager.resolveEvents();


        eventManager.addEvent(new GameBeginEvent(this, this.getStartingPlayer()));
        eventManager.resolveEvents();

        System.out.println("Player " + turnController.getWhoseTurn() + "'s turn");
        eventManager.addEvent(new TurnBeginEvent(this, this.getActivePlayer()));
        eventManager.resolveEvents();
        // Main loop
        while (turnController.TwoOrMorePlayers()) {
            // Beginning of step
            // add start of phase event
            emitStartOfPhaseEvent(turnController.getPhase());
            updateBoardState();
            System.out.println("Current Phase: " + turnController.getPhase());

            if (turnController.isPriorityPhase() || !data.isStackEmpty()) {
                // Loop until priority has finished
                while (true) {
                    // Active player gets priority
                    turnController.ResetPriority();
                    eventManager.addEvent(new PlayerGainPriorityEvent(this, getActivePlayer()));
                    System.out.println("Player " + turnController.getPriorityIndex() + " has priority.");
                    updateBoardState();

                    int passCount = 0;
                    // Loop until all players have passed in a row
                    while (true) {
                        Player prio_player = getPriorityPlayer();
                        // Loop until current player has passed
                        while (true) {
                            checkStateBasedActions();

                            // TODO: flesh out player actions
                            boolean passed = false;
                            boolean invalidInput;

                            System.out.println("\n\n\n\n\n\n\n");
                            System.out.println("Current Phase: " + turnController.getPhase());
                            System.out.println(data);
                            do {
                                invalidInput = false;
                                int input = getPlayerInputChoice(
                                        prio_player,
                                        "Choose an action",
                                        List.of(
                                                "Pass",
                                                "Activate an ability",
                                                "Cast a spell",
                                                "Do a special action"
                                        ),
                                        false);
                                switch (input) {
                                    case 0: // pass
                                        passed = true;
                                        break;
                                    case 1: {
                                        // activate ability
                                        List<Card> card_choices = data.battlefield.stream()
                                                .filter((c) -> c.controller == prio_player
                                                        && c.status.phasedIn
                                                        && !c.abilities.activatedAbilities.isEmpty())
                                                .collect(Collectors.toList());

                                        int choice = getPlayerInputChoice(
                                                prio_player,
                                                "Choose a card with an ability to activate",
                                                card_choices,
                                                true);
                                        Card source = card_choices.get(choice);
                                        int ability_choice = getPlayerInputChoice(
                                                prio_player,
                                                "Choose an ability to activate",
                                                source.abilities.activatedAbilities,
                                                true);
                                        if (ability_choice == -1) {
                                            invalidInput = true;
                                            System.out.println("Activate ability cancelled.");
                                            break;
                                        }
                                        eventManager.addEvent(
                                                new ActivateAbilityEvent(
                                                        this, source, prio_player,
                                                        source.abilities.activatedAbilities.get(ability_choice)
                                                )
                                        );
                                        updateBoardState();
                                        break;
                                    }
                                    case 2: {
                                        // cast spell
                                        break;
                                    }
                                    case 3: {
                                        // special action
                                        // TODO flesh out special actions
                                        //      for now, only playing a land is allowed
                                        if (prio_player != getActivePlayer()) {
                                            invalidInput = true;
                                            System.out.println("It is not your turn");
                                            break;
                                        }
                                        if (turnController.getPhase() != PhaseType.Main1 && turnController.getPhase() != PhaseType.Main2) {
                                            invalidInput = true;
                                            System.out.println("You can only play lands during your main phase");
                                            break;
                                        }
                                        if (prio_player.data.landsPlayedThisTurn >= prio_player.data.landDropsPerTurn) {
                                            invalidInput = true;
                                            System.out.println("You have used all of your land drops this turn.");
                                            break;
                                        }

                                        List<Card> landsInHand = prio_player.data.hand.stream().filter((c) -> c.typeline.isType("Land")).collect(Collectors.toList());
                                        if (landsInHand.isEmpty()) {
                                            invalidInput = true;
                                            System.out.println("You have no lands in hand");
                                            break;
                                        }
                                        int land_choice = getPlayerInputChoice(
                                                prio_player,
                                                "Choose a land to play",
                                                landsInHand,
                                                true);
                                        if (land_choice == -1) {
                                            invalidInput = true;
                                            System.out.println("Special action cancelled.");
                                            break;
                                        }
                                        eventManager.addEvent(new PlayLandEvent(this, prio_player, landsInHand.get(land_choice)));
                                        updateBoardState();
                                        // Player keeps prio, let them go again
                                        break;
                                    }
                                }
                            } while (invalidInput);

                            if (passed) {
                                break;
                            } else {
                                passCount = 0;
                            }

                        }
                        passCount++;
                        eventManager.addEvent(new PlayerLosePriorityEvent(this, getPriorityPlayer()));
                        System.out.println("Player " + turnController.getPriorityIndex() + " passed. ");
                        updateBoardState();


                        // If all players pass in a row, then go to next phase
                        if (passCount == turnController.numPlayersAlive()) {
                            break;
                        } else {
                            // Next priority
                            turnController.IncPriority();
                            eventManager.addEvent(new PlayerGainPriorityEvent(this, getPriorityPlayer()));
                            System.out.println("Player " + turnController.getPriorityIndex() + " has priority.");
                            updateBoardState();
                        }
                    }
                    if (!data.isStackEmpty()) {
                        // TODO: resolve it
                        eventManager.addEvent(
                                new ResolveStackOnce(this)
                        );
                        updateBoardState();
                    } else {
                        break;
                    }
                }
            }

            // ============================================
            // End of step
            // ============================================
            emitEndOfPhaseEvent(turnController.getPhase());
            boolean turnEnded = turnController.IncPhase();
            updateBoardState();
            if (turnEnded) {
                endOfTurn();

                eventManager.addEvent(new TurnEndEvent(this, getActivePlayer()));
                System.out.println("\n\n\nPlayer " + turnController.getWhoseTurn() + " ends their turn");
                updateBoardState();

                this.turnController.IncTurn();

                eventManager.addEvent(new TurnBeginEvent(this, getActivePlayer()));
                System.out.println("\n\n\nPlayer " + turnController.getWhoseTurn() + "'s turn");
                updateBoardState();
            }
        }
    }

    private void initTurnBasedActions() {
        eventManager.dispatcher.addTurnBasedActionListener(PhaseInForTurnAction.type, PhaseInForTurnAction.turnBasedAction);
        eventManager.dispatcher.addTurnBasedActionListener(UntapForTurnAction.type, UntapForTurnAction.turnBasedAction);
        eventManager.dispatcher.addTurnBasedActionListener(DrawCardForTurnAction.type, DrawCardForTurnAction.turnBasedAction);
        eventManager.dispatcher.addTurnBasedActionListener(DeclareAttackersTurnAction.type, DeclareAttackersTurnAction.turnBasedAction);
        eventManager.dispatcher.addTurnBasedActionListener(DeclareBlockersTurnAction.type, DeclareBlockersTurnAction.turnBasedAction);
        eventManager.dispatcher.addTurnBasedActionListener(AssignCombatDamageTurnAction.type, AssignCombatDamageTurnAction.turnBasedAction);
        eventManager.dispatcher.addTurnBasedActionListener(DealCombatDamageTurnAction.type, DealCombatDamageTurnAction.turnBasedAction);
        eventManager.dispatcher.addTurnBasedActionListener(DiscardToHandSizeTurnAction.type, DiscardToHandSizeTurnAction.turnBasedAction);
        eventManager.dispatcher.addTurnBasedActionListener(EndUntilEndOfTurnAndDamageTurnAction.type, EndUntilEndOfTurnAndDamageTurnAction.turnBasedAction);
        eventManager.dispatcher.addTurnBasedActionListener(EmptyManaPoolTurnAction.type, EmptyManaPoolTurnAction.turnBasedAction);

    }

    // Returns -1 if there were no options to choose from
    // or if player decided to not make a choice (may or may not be legal to do so)
    // Otherwise returns index of the chosen option
    public int getPlayerInputChoice(Player player, String prompt, List<?> options, boolean allow_nochoice) {
        if (options.isEmpty()) return -1;
        System.out.println("Player " + player.index + " must make a choice:");
        System.out.println(prompt);

        if (allow_nochoice) System.out.println("-1: No choice.");

        int i = 0;
        for (Object o : options) {
            System.out.println(i + ": " + o.toString());
            i++;
        }

        int choice = -1;
        boolean valid_choice = false;
        while (!valid_choice) {
            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
                if (choice >= options.size() || choice < -1 || (!allow_nochoice && choice == -1)) {
                    System.out.println("Please choose a valid option.");
                } else {
                    valid_choice = true;
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }
        return choice;
    }

    public List<Integer> getPlayerInputDamageAssignment(Player player, String prompt, int damage, List<GameObjectOrPlayer> combatants) {
        ArrayList<Integer> choices = new ArrayList<>();
        for (int i = 0; i < combatants.size(); i++) {
            choices.add(0);
        }
        if (damage == 0) return choices;
        choices = new ArrayList<>();
        System.out.println("Player " + player.index + " must make an assignment:");
        System.out.println(prompt);

        int i = 0;
        for (GameObjectOrPlayer o : combatants) {
            System.out.println(i + ": " + o.toString());
            i++;
        }

        int sum = 0;
        i = 0;
        while (i < combatants.size()) {
            try {
                String input = scanner.nextLine();
                int amount = Integer.parseInt(input);
                if (amount < 0 || sum + amount > damage) {
                    throw new NumberFormatException();
                } else {
                    sum += amount;
                    choices.add(amount);
                    i++;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid assignment.");
            }
        }
        return choices;
    }



    public Player getStartingPlayer() {
        return data.players.get(0);
    }
    public Player getActivePlayer() {
        return data.players.get(turnController.getWhoseTurn());
    }
    public Player getPriorityPlayer() {
        return data.players.get(turnController.getPriorityIndex());
    }


    public void emitStartOfPhaseEvent(PhaseType phase) {
        switch (phase) {
            case Untap:
                // Phasing happens
                // All permanents untap
                // No priority
                this.eventManager.addEvent(new UntapStepBeginEvent(this));
                break;
            case Upkeep:
                // AP Priority
                this.eventManager.addEvent(new UpkeepBeginEvent(this));
                break;
            case Draw:
                // The active player draws a card
                // AP Priority
                this.eventManager.addEvent(new DrawStepBeginEvent(this));
                break;
            case Main1:
                // Increment and resolve sagas
                // AP Priority
                this.eventManager.addEvent(new MainPreCombatPhaseBeginEvent(this));
                break;
            case CombatStart:
                // AP Priority
                this.eventManager.addEvent(new StartCombatPhaseBeginEvent(this));
                break;
            case CombatAttackers:
                // Attackers are declared by AP
                // AP Priority
                this.eventManager.addEvent(new DeclareAttackersStepBeginEvent(this));
                break;
            case CombatBlockers:
                // Blockers are declared
                // Attacking player chooses damage assignment order of attackers
                // Defending player chooses damage assignment order of blockers
                // AP Priority
                this.eventManager.addEvent(new DeclareBlockersStepBeginEvent(this));
                break;
            case CombatDamage:
                // Attacking player assigns combat damage
                // Defending player assigns combat damage
                // Damage is dealt (simultaneously)
                // AP Priority
                this.eventManager.addEvent(new CombatDamageStepBeginEvent(this));
                break;
            case CombatEnd:
                // AP Priority
                this.eventManager.addEvent(new EndCombatPhaseBeginEvent(this));
                break;
            case Main2:
                // AP Priority
                this.eventManager.addEvent(new MainPostCombatPhaseBeginEvent(this));
                break;
            case End:
                // AP Priority
                this.eventManager.addEvent(new EndPhaseBeginEvent(this));
                break;
            case Cleanup:
                // AP Player discards to max hand size
                // All marked damage is removed
                // No priority
                this.eventManager.addEvent(new CleanupStepBeginEvent(this));
                break;
        }
    }
    public void emitEndOfPhaseEvent(PhaseType phase) {
        switch (phase) {
            case Untap:
                this.eventManager.addEvent(new UntapStepEndEvent(this));
                break;
            case Upkeep:
                this.eventManager.addEvent(new UpkeepEndEvent(this));
                break;
            case Draw:
                this.eventManager.addEvent(new DrawStepEndEvent(this));
                break;
            case Main1:
                this.eventManager.addEvent(new MainPreCombatPhaseEndEvent(this));
                break;
            case CombatStart:
                this.eventManager.addEvent(new StartCombatPhaseEndEvent(this));
                break;
            case CombatAttackers:
                this.eventManager.addEvent(new DeclareAttackersStepEndEvent(this));
                break;
            case CombatBlockers:
                this.eventManager.addEvent(new DeclareBlockersStepEndEvent(this));
                break;
            case CombatDamage:
                this.eventManager.addEvent(new CombatDamageStepEndEvent(this));
                break;
            case CombatEnd:
                this.eventManager.addEvent(new EndCombatPhaseEndEvent(this));
                break;
            case Main2:
                this.eventManager.addEvent(new MainPostCombatPhaseEndEvent(this));
                break;
            case End:
                this.eventManager.addEvent(new EndPhaseEndEvent(this));
                break;
            case Cleanup:
                this.eventManager.addEvent(new CleanupStepEndEvent(this));
                break;
        }
    }


    public void updateBoardState() {
        eventManager.applyReplacementEffects(data.getReplacementEffects());
        eventManager.resolveEvents();
        data.updateBoardState();
    }



    public void checkStateBasedActions() {
        this.data.updateBoardState();
        // TODO: implement state based actions
    }



    public void endOfTurn() {
        // TODO: empty mana pools
        // TODO: end of turn effects end
    }

    public void resolveTopOfStack() {

    }


    // Thread Local stuff
//    private static final ThreadLocal<MTGGame> _localStorage = new ThreadLocal<MTGGame>(){
//        protected MTGGame initialValue() {
//            return new MTGGame(Variant.Commander, List.of(List.of(),List.of(),List.of(),List.of()));
//        }
//    };
//
//    public static MTGGame getInstance() {
//        return _localStorage.get();
//    }
//
//    public static GameData data(){
//        return _localStorage.get().gameData;
//    }
//
//    public static void setInstance(Variant variant, List<List<OracleCard>> decklists){
//        _localStorage.set(new MTGGame(variant, decklists));
//    }
}
