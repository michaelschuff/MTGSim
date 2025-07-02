package org.koi.game;

import org.koi.event.EventManager;
import org.koi.event.spell.ResolveStackOnceEvent;
import org.koi.event.spell.SpellWasCastEvent;
import org.koi.event.statebasedactions.*;
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
import org.koi.event.turnbasedaction.*;
import org.koi.event.zonechange.DrawCardEvent;
import org.koi.event.zonechange.ObjectMoveZoneEvent;
import org.koi.event.zonechange.action.ActivateAbilityEvent;
import org.koi.event.zonechange.action.PlayLandEvent;
import org.koi.gameobject.card.Card;
import org.koi.gameobject.card.OracleCard;
import org.koi.game.turn.PhaseType;
import org.koi.game.turn.TurnController;
import org.koi.util.GameObjectOrPlayer;
import org.koi.util.Player;
import org.koi.util.Variant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.koi.cards.CardLoader.loadCard;

public class MTGGame {
    public TurnController turnController;
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
        this.initStateBasedActions();
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
                                        //TODO: Check for required actions
                                        passed = true;
                                        break;
                                    case 1: {
                                        // activate ability
                                        invalidInput = !AttemptAbilityActivation(prio_player);
                                        break;
                                    }
                                    case 2: {
                                        // cast spell
                                        invalidInput = !AttemptSpellCast(prio_player);
                                        break;
                                    }
                                    case 3: {
                                        // special action
                                        invalidInput = !AttemptSpecialAction(prio_player);
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
                                new ResolveStackOnceEvent(this)
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
            updateBoardState();
            boolean turnEnded = turnController.IncPhase();
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


    // Returns whether the ability was successfully activated or not
    private boolean AttemptAbilityActivation(Player activator) {
        List<Card> card_choices = data.battlefield.stream()
                .filter((c) -> canActivateAbility(activator, c))
                .collect(Collectors.toList());

        int choice = getPlayerInputChoice(
                activator,
                "Choose a card with an ability to activate",
                card_choices,
                true);
        Card source = card_choices.get(choice);
        int ability_choice = getPlayerInputChoice(
                activator,
                "Choose an ability to activate",
                source.abilities.activatedAbilities,
                true);
        if (ability_choice == -1) {
            System.out.println("Activate ability cancelled.");
            return false;
        }


        eventManager.addEvents(source.abilities.activatedAbilities.get(ability_choice).cost);
        updateBoardState();
        eventManager.addEvent(
                new ActivateAbilityEvent(
                        this, source, activator,
                        source.abilities.activatedAbilities.get(ability_choice)
                )
        );
        updateBoardState();
        return true;
    }

    // Returns whether the spell was successfully activated or not
    private boolean AttemptSpellCast(Player caster) {
        List<Card> card_choices = caster.data.hand.stream()
                .filter((c) -> !c.typeline.isType("Land"))
                .collect(Collectors.toList());

        int choice = getPlayerInputChoice(
                caster,
                "Choose a card to cast",
                card_choices,
                true);
        Card spell = card_choices.get(choice);

        /** 601.2a
         * Move the card onto the stack and apply any relevant continuous effects
         */
        eventManager.addEvent(
                new ObjectMoveZoneEvent(
                        this, caster.asGameObjectOrPlayer(),
                        spell, caster.data.hand, data.theStack
                )
        );
        eventManager.resolveEvents();

        /**
         * 601.2b
         * TODO: Announce modal
         * TODO: Announce splice
         * TODO: Announce alternative/additional costs (buyback, kicker)
         *       note: only 1 alternative method/alternative cost allowed
         * TODO: Announce {X} values (and other variables)
         *       note: if {X} would depend on a choice that would happen later
         *       in these steps, then make that choice now
         * TODO: Announce hybric mana cost choices
         * TODO: Announce phyrexian mana cost choices
         */

        /**
         * 601.2c.
         * TODO: Announce selection of all appropriate targets
         *       note: if variable amount of targets, then choose amount first
         *              once determined, number of targets will not change
         *       note: only choose the same object once per instance of the word "target"
         *       note: if a target "must" be chosen, pick targets to satisfy the maximum amount
         *              without violating any "cant be targeted"
         * TODO: selected targets become targets, queue any triggered abilities
         */

        /**
         * 601.2d.
         * TODO: Divide or distribute damage or an effect.
         *      note: each target must receive at least 1
         */

        /**
         * 601.2e.
         * Check if the spell can legally be cast.
         * // TODO: If not, return to moment before it was cast
         */
        if (!canCastSpell(caster, spell)) {
            // return here
            // will involve moving spell back to hand
            // and clearing queue of triggered events
            // potentially even undoing any other events,
            // if they replaced moving the spell onto the stack
        }

        /**
         * 601.2f.
         * TODO: Determine total cost. "Lock" it in
         */

        /**
         * 601.2g.
         * TODO: Allow player to activate mana abilities
         */

        /**
         * 601.2h.
         * TODO: Player pays all costs
         *      note: first, pay costs that don't involve random elements or
         *            moving objects from the library to a public zone, in any order
         *      note: second, pay all remaining costs in any order. Partial payments are not allowed
         *            unpayable costs can't be paid
         */

        /**
         * 601.2i
         * TODO: apply effects that modify characteristics of the spell, and the spell becomes "cast"
         *       note: queue abilities that trigger of cast now
         *       note: player receives priority again
         */

        eventManager.addEvent(new SpellWasCastEvent(this, spell));
        updateBoardState();
        return true;
    }

    private boolean AttemptSpecialAction(Player player) {
        // TODO flesh out special actions
        //      for now, only playing a land is allowed
        if (player != getActivePlayer()) {
            System.out.println("It is not your turn");
            return false;
        }
        if (turnController.getPhase() != PhaseType.Main1 && turnController.getPhase() != PhaseType.Main2) {
            System.out.println("You can only play lands during your main phase");
            return false;
        }
        if (player.data.landsPlayedThisTurn >= player.data.landDropsPerTurn) {
            System.out.println("You have used all of your land drops this turn.");
            return false;
        }

        List<Card> landsInHand = player.data.hand.stream().filter((c) -> c.typeline.isType("Land")).collect(Collectors.toList());
        if (landsInHand.isEmpty()) {
            System.out.println("You have no lands in hand");
            return false;
        }
        int land_choice = getPlayerInputChoice(
                player,
                "Choose a land to play",
                landsInHand,
                true);
        if (land_choice == -1) {
            System.out.println("Special action cancelled.");
            return false;
        }
        eventManager.addEvent(new PlayLandEvent(this, player, landsInHand.get(land_choice)));
        updateBoardState();


        // Player keeps prio, let them go again
        return false;
    }


    public boolean canCastSpell(Player p, Card c) {
        /**
         * 601.3.
         * TODO: A player can begin to cast a spell iff
         *           a rule allows them to and
         *           no rule or effect disallows them to
         */
        return true;
    }
    public boolean canActivateAbility(Player p, Card c) {
        return c.controller == p && c.status.phasedIn && !c.abilities.activatedAbilities.isEmpty();
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
        eventManager.dispatcher.addTurnBasedActionListener(ResetCombatStatusTurnAction.type, ResetCombatStatusTurnAction.turnBasedAction);
    }

    private void initStateBasedActions() {
        eventManager.dispatcher.addTurnBasedActionListener(PlayerGainPriorityEvent.class, PlayerZeroLifeSBA.get());
        eventManager.dispatcher.addTurnBasedActionListener(PlayerGainPriorityEvent.class, DrawFromEmptyLibrarySBA.get());
        eventManager.dispatcher.addTurnBasedActionListener(PlayerGainPriorityEvent.class, PoisonCountersSBA.get());
        eventManager.dispatcher.addTurnBasedActionListener(PlayerGainPriorityEvent.class, IllegalTokenLocationsSBA.get());
        eventManager.dispatcher.addTurnBasedActionListener(PlayerGainPriorityEvent.class, IllegalCopyLocationSBA.get());
        eventManager.dispatcher.addTurnBasedActionListener(PlayerGainPriorityEvent.class, CreatureZeroToughnessSBA.get());
        eventManager.dispatcher.addTurnBasedActionListener(PlayerGainPriorityEvent.class, CreatureDealtLethalDamageSBA.get());
        eventManager.dispatcher.addTurnBasedActionListener(PlayerGainPriorityEvent.class, CreatureReceivedDeathtouchDamageSBA.get());
        eventManager.dispatcher.addTurnBasedActionListener(PlayerGainPriorityEvent.class, PlaneswalkerZeroLoyaltySBA.get());
        eventManager.dispatcher.addTurnBasedActionListener(PlayerGainPriorityEvent.class, LegendRuleSBA.get());
        eventManager.dispatcher.addTurnBasedActionListener(PlayerGainPriorityEvent.class, WorldRuleSBA.get());
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



    public void endOfTurn() {
        // TODO: empty mana pools
        // TODO: end of turn effects end
    }
}
