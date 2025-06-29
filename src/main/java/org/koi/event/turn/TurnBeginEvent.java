package org.koi.event.turn;

import org.koi.event.Event;
import org.koi.game.MTGGame;
import org.koi.util.Player;


public class TurnBeginEvent extends TemporalEvent {
    public Player activePlayer;
    public TurnBeginEvent(MTGGame game, Player activePlayer) {
        super(game);
        this.activePlayer = activePlayer;
    }

    @Override
    public boolean resolve() {
        return true;
    }

}
