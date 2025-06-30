package org.koi.event.turnbasedaction;

import org.koi.event.Event;
import org.koi.event.EventListener;
import org.koi.event.combat.AttackerAssignCombatDamageEvent;
import org.koi.event.combat.BlockerAssignCombatDamageEvent;
import org.koi.event.turn.combat.CombatDamageStepBeginEvent;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.GameObjectOrPlayer;
import org.koi.util.Player;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AssignCombatDamageTurnAction {
    public static Class<CombatDamageStepBeginEvent> type = CombatDamageStepBeginEvent.class;
    public static EventListener turnBasedAction = new EventListener() {
        @Override
        public void accept(MTGGame game, Event event) {
            Player ap = game.getActivePlayer();

            List<Card> attackers = game.data.battlefield.stream()
                    .filter((c) -> c.status.attacking && c.status.phasedIn)
                    .collect(Collectors.toList());

            List<Card> blockers = game.data.battlefield.stream()
                    .filter((c) -> c.status.blocking && c.status.phasedIn)
                    .collect(Collectors.toList());

            for (Player p : game.data.players) {
                if (p == ap) {
                    // attacking
                    for (Card c : attackers) {
                        List<GameObjectOrPlayer> combatants = c.status.inCombatWith.stream().map(Card::asGameObjectOrPlayer).collect(Collectors.toList());
                        combatants.add(c.status.attackTarget);
                        if (c.pt.power() > 0) {
                            List<Integer> assignment = game.getPlayerInputDamageAssignment(
                                    p, "Assign combat damage", c.pt.power(), combatants
                            );
                            c.status.damageAssignment = assignment;
                            game.eventManager.addEvent(
                                    new AttackerAssignCombatDamageEvent(game, c, combatants, assignment)
                            );
                        }
                    }
                } else {
                    // blocking
                    blockers = blockers.stream().filter((c) -> c.controller == p).collect(Collectors.toList());
                    for (Card c : blockers) {
                        List<GameObjectOrPlayer> combatants = c.status.inCombatWith.stream().map(Card::asGameObjectOrPlayer).collect(Collectors.toList());
                        if (c.pt.power() > 0) {
                            List<Integer> assignment = game.getPlayerInputDamageAssignment(
                                    p, "Assign combat damage", c.pt.power(), combatants
                            );

                            c.status.damageAssignment = assignment;
                            game.eventManager.addEvent(
                                    new BlockerAssignCombatDamageEvent(game, c, combatants, assignment)
                            );
                        }
                    }
                }
            }
        }
    };
}
