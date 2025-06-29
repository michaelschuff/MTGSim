package org.koi.event.turn.beginning;

import org.koi.event.Event;
import org.koi.event.turn.PhaseBeginEvent;
import org.koi.game.MTGGame;

public class DrawStepBeginEvent extends PhaseBeginEvent {
    public DrawStepBeginEvent(MTGGame game) {
        super(game);
    }
    @Override
    public boolean resolve() {
        return true;
    }
}