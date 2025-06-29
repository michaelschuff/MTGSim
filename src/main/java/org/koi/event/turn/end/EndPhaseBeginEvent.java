package org.koi.event.turn.end;

import org.koi.event.Event;
import org.koi.event.turn.PhaseBeginEvent;
import org.koi.game.MTGGame;

public class EndPhaseBeginEvent extends PhaseBeginEvent {
    public EndPhaseBeginEvent(MTGGame game) {
        super(game);
    }
    @Override
    public boolean resolve() {
        return true;
    }
}