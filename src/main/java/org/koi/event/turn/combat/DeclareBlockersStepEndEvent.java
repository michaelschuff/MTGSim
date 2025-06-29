package org.koi.event.turn.combat;

import org.koi.event.Event;
import org.koi.event.turn.PhaseEndEvent;
import org.koi.game.MTGGame;


public class DeclareBlockersStepEndEvent extends PhaseEndEvent {
    public DeclareBlockersStepEndEvent(MTGGame game) {
        super(game);
    }
    @Override
    public boolean resolve() {
        return true;
    }
}