package org.koi.event.turn.end;

import org.koi.event.Event;
import org.koi.event.turn.PhaseEndEvent;
import org.koi.game.MTGGame;

public class EndPhaseEndEvent extends PhaseEndEvent {
    public EndPhaseEndEvent(MTGGame game) {
        super(game);
    }

    @Override
    public boolean resolve() {
        return true;
    }
}
