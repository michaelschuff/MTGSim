package org.koi.event.turn.main;

import org.koi.event.Event;
import org.koi.event.turn.PhaseBeginEvent;
import org.koi.game.MTGGame;

public class MainPreCombatPhaseBeginEvent extends PhaseBeginEvent {
    public MainPreCombatPhaseBeginEvent(MTGGame game) {
        super(game);
    }
    @Override
    public boolean resolve() {
        return true;
    }
}