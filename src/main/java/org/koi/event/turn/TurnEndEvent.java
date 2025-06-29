package org.koi.event.turn;

import org.koi.event.Event;
import org.koi.game.MTGGame;
import org.koi.util.Player;

public class TurnEndEvent extends PhaseEndEvent {
    public Player activePlayer;
    public TurnEndEvent(MTGGame game, Player activePlayer) {
        super(game);
        this.activePlayer = activePlayer;
    }

    @Override
    public boolean resolve() {
        return true;
    }

}
