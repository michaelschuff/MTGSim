package org.koi.event.turn;

import org.koi.event.Event;
import org.koi.game.MTGGame;

public abstract class PhaseBeginEvent extends TemporalEvent {
    public PhaseBeginEvent(MTGGame game) {
        super(game);
    }
}
