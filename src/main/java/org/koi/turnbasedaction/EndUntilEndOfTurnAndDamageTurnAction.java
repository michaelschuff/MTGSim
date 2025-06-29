package org.koi.turnbasedaction;

import org.koi.event.Event;
import org.koi.event.EventListener;
import org.koi.event.damage.RemoveMarkedDamageEvent;
import org.koi.event.turn.end.CleanupStepBeginEvent;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;

public abstract class EndUntilEndOfTurnAndDamageTurnAction {
    public static Class<CleanupStepBeginEvent> type = CleanupStepBeginEvent.class;
    public static EventListener turnBasedAction = new EventListener() {
        @Override
        public void accept(MTGGame game, Event event) {
            for (Card c : game.data.battlefield) {
                if (c.status.damage != 0 && c.status.phasedIn) {
                    game.eventManager.addEvent(
                            new RemoveMarkedDamageEvent(game, c, -1)
                    );
                }
            }
            game.getActivePlayer().data.landsPlayedThisTurn = 0;

            // TODO: remove until end of turn effects.
            //       also implement until end of turn effects.
        }
    };
}
