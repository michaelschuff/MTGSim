package org.koi.event.turn.beginning;

import org.koi.event.Event;
import org.koi.event.turn.PhaseBeginEvent;
import org.koi.game.MTGGame;

public class UpkeepBeginEvent extends PhaseBeginEvent {
    public UpkeepBeginEvent(MTGGame game) {
        super(game);
    }
    @Override
    public boolean resolve() {
        return true;
    }
}
