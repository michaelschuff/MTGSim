package org.koi.event.turnbasedaction;

import org.koi.event.Event;
import org.koi.event.EventListener;
import org.koi.event.mana.ManaLeavesPoolEvent;
import org.koi.event.turn.PhaseEndEvent;
import org.koi.game.MTGGame;
import org.koi.util.Player;

public abstract class EmptyManaPoolTurnAction {
    public static Class<PhaseEndEvent> type = PhaseEndEvent.class;
    public static EventListener turnBasedAction = new EventListener() {
        @Override
        public void accept(MTGGame game, Event event) {
            for (Player p : game.data.players) {
                game.eventManager.addEvent(new ManaLeavesPoolEvent(game, p, null));
            }
            game.eventManager.resolveEvents();
        }
    };
}
