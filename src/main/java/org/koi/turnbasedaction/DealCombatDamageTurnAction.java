package org.koi.turnbasedaction;

import org.koi.event.Event;
import org.koi.event.EventListener;
import org.koi.event.turn.combat.CombatDamageStepBeginEvent;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.Player;

import java.util.List;
import java.util.stream.Collectors;

public abstract class DealCombatDamageTurnAction {
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
            while (true) {

            }

        }
    };
}
