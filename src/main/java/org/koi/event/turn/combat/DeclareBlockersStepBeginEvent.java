package org.koi.event.turn.combat;

import org.koi.event.Event;
import org.koi.event.turn.PhaseBeginEvent;
import org.koi.game.MTGGame;

public class DeclareBlockersStepBeginEvent extends PhaseBeginEvent {
    public DeclareBlockersStepBeginEvent(MTGGame game) {
        super(game);
    }
    @Override
    public boolean resolve() {
        return true;
    }
}