package org.koi.event.turn.beginning;

import org.koi.event.Event;
import org.koi.event.turn.PhaseEndEvent;
import org.koi.game.MTGGame;


public class DrawStepEndEvent extends PhaseEndEvent {
    public DrawStepEndEvent(MTGGame game) {
        super(game);
    }
    @Override
    public boolean resolve() {
        return true;
    }
}