package org.koi.event.turn.beginning;

import org.koi.event.Event;
import org.koi.event.turn.PhaseEndEvent;
import org.koi.game.MTGGame;

public class BeginningPhaseEndEvent extends PhaseEndEvent {
    public BeginningPhaseEndEvent(MTGGame game) {
        super(game);
    }
    @Override
    public boolean resolve() {
        return true;
    }
}
