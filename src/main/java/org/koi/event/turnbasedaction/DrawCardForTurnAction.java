package org.koi.event.turnbasedaction;

import org.koi.event.Event;
import org.koi.event.EventListener;
import org.koi.event.turn.beginning.DrawStepBeginEvent;
import org.koi.event.zonechange.DrawCardEvent;
import org.koi.game.MTGGame;

public abstract class DrawCardForTurnAction {
    public static Class<DrawStepBeginEvent> type = DrawStepBeginEvent.class;
    public static EventListener turnBasedAction = new EventListener() {
        @Override
        public void accept(MTGGame game, Event event) {
            game.eventManager.addEvent(new DrawCardEvent(game, game.getActivePlayer()));
        }
    };
}
