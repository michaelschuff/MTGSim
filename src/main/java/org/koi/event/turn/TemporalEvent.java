package org.koi.event.turn;

import org.koi.event.Event;
import org.koi.game.MTGGame;

public abstract class TemporalEvent extends Event {
    public TemporalEvent(MTGGame game) {
        super(game);
    }
}
