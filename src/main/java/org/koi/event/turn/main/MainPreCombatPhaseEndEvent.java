package org.koi.event.turn.main;


import org.koi.event.Event;
import org.koi.event.turn.PhaseEndEvent;
import org.koi.game.MTGGame;

public class MainPreCombatPhaseEndEvent extends PhaseEndEvent {
    public MainPreCombatPhaseEndEvent(MTGGame game) {
        super(game);
    }
    @Override
    public boolean resolve() {
        return true;
    }
}
