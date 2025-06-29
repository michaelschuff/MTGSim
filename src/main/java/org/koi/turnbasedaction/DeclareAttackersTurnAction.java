package org.koi.turnbasedaction;

import org.koi.event.Event;
import org.koi.event.EventListener;
import org.koi.event.combat.DeclareAttackerEvent;
import org.koi.event.turn.combat.DeclareAttackersStepBeginEvent;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.GameObjectOrPlayer;
import org.koi.util.Player;

import java.util.List;
import java.util.stream.Collectors;

public abstract class DeclareAttackersTurnAction {
    public static Class<DeclareAttackersStepBeginEvent> type = DeclareAttackersStepBeginEvent.class;
    public static EventListener turnBasedAction = new EventListener() {
        @Override
        public void accept(MTGGame game, Event event) {
            Player ap = game.getActivePlayer();
            List<GameObjectOrPlayer> cardTargets = game.data.battlefield.stream()
                    .filter((c) ->
                            (c.typeline.isType("Battle") || c.typeline.isType("Planeswalker"))
                                    && c.controller != ap)
                    .map(Card::asGameObjectOrPlayer)
                    .collect(Collectors.toList());
            List<GameObjectOrPlayer> targets = game.data.players
                    .stream()
                    .filter((p) -> p != ap)
                    .map(Player::asGameObjectOrPlayer)
                    .collect(Collectors.toList());
            targets.addAll(cardTargets);
            List<Card> valid_attackers = game.data.battlefield
                    .stream()
                    .filter((c) -> c.controller == ap && c.typeline.isType("Creature"))
                    .collect(Collectors.toList());
            int creature_choice;
            while (true) {
                creature_choice = game.getPlayerInputChoice(
                        ap,"Choose a creature to attack",
                        valid_attackers, true);
                if (creature_choice != -1) {
                    int target_choice = game.getPlayerInputChoice(
                            ap, "Choose a player to attack",
                            targets, false);
                    game.eventManager.addEvent(
                            new DeclareAttackerEvent(
                                    game,
                                    valid_attackers.get(creature_choice),
                                    targets.get(target_choice)
                            )
                    );
                } else {
                    break;
                }
            }

        }
    };
}
