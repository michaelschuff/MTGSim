package org.koi.event.turn;

import org.koi.game.MTGGame;

public abstract class PhaseEndEvent extends TemporalEvent {
    public PhaseEndEvent(MTGGame game) {
        super(game);
    }
}
