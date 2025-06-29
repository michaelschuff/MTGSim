package org.koi.event.turn.beginning;

import org.koi.event.Event;
import org.koi.event.turn.PhaseEndEvent;
import org.koi.game.MTGGame;

public class UntapStepEndEvent extends PhaseEndEvent {
    public UntapStepEndEvent(MTGGame game) {
        super(game);
    }
    @Override
    public boolean resolve() {
        return true;
    }
}
