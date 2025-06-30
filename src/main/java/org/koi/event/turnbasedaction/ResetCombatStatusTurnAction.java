package org.koi.event.turnbasedaction;

import org.koi.event.Event;
import org.koi.event.EventListener;
import org.koi.event.turn.combat.EndCombatPhaseEndEvent;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;

public abstract class ResetCombatStatusTurnAction {
    public static Class<EndCombatPhaseEndEvent> type = EndCombatPhaseEndEvent.class;
    public static EventListener turnBasedAction = new EventListener() {
        @Override
        public void accept(MTGGame game, Event event) {
            for (Card c : game.data.battlefield) {
                c.status.attackTarget = null;
                c.status.inCombatWith = null;
                c.status.attacking = false;
                c.status.blocking = false;
            }
        }
    };
}