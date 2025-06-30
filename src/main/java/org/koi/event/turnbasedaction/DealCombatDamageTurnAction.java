package org.koi.event.turnbasedaction;

import org.koi.event.Event;
import org.koi.event.EventListener;
import org.koi.event.damage.DealCombatDamageToCardEvent;
import org.koi.event.damage.DealCombatDamageToPlayerEvent;
import org.koi.event.turn.combat.CombatDamageStepBeginEvent;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.Player;

import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public abstract class DealCombatDamageTurnAction {
    public static Class<CombatDamageStepBeginEvent> type = CombatDamageStepBeginEvent.class;
    public static EventListener turnBasedAction = new EventListener() {
        @Override
        public void accept(MTGGame game, Event event) {
            List<Card> attackers = game.data.battlefield.stream()
                    .filter((c) -> c.status.attacking && c.status.phasedIn)
                    .collect(Collectors.toList());
            List<Card> blockers = game.data.battlefield.stream()
                    .filter((c) -> c.status.blocking && c.status.phasedIn)
                    .collect(Collectors.toList());


            // attackers deal damage events
            for (Card attacker : attackers) {
                int power = attacker.pt.power();
                ListIterator<Card> iter = attacker.status.inCombatWith.listIterator();
                while (iter.hasNext()) {
                    int amount = attacker.status.damageAssignment.get(iter.nextIndex());
                    power -= amount;
                    game.eventManager.addEvent(
                            new DealCombatDamageToCardEvent(
                                    game, attacker,
                                    iter.next(), amount
                            )
                    );
                }


                // extra damage is assumed to be assigned to defending object
                if (power > 0) {
                    if (attacker.status.attackTarget.isPlayer()) {
                        game.eventManager.addEvent(
                                new DealCombatDamageToPlayerEvent(
                                        game, attacker,
                                        attacker.status.attackTarget.player,
                                        power
                                )
                        );
                    } else {
                        game.eventManager.addEvent(
                                new DealCombatDamageToCardEvent(
                                        game, attacker,
                                        (Card) attacker.status.attackTarget.o,
                                        power
                                )
                        );
                    }
                }
            }


            // blockers deal damage events
            for (Card blocker : blockers) {
                int power = blocker.pt.power();
                ListIterator<Card> iter = blocker.status.inCombatWith.listIterator();
                while (iter.hasNext()) {
                    int amount = blocker.status.damageAssignment.get(iter.nextIndex());
                    power -= amount;
                    game.eventManager.addEvent(
                            new DealCombatDamageToCardEvent(
                                    game, blocker,
                                    iter.next(), amount
                            )
                    );
                }
            }

        }
    };
}
