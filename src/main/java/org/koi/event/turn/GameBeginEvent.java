package org.koi.event.turn;

import org.koi.event.Event;
import org.koi.game.MTGGame;
import org.koi.util.Player;


public class GameBeginEvent extends TemporalEvent {
    public Player startingPlayer;
    public GameBeginEvent(MTGGame game, Player startingPlayer) {
        super(game);
        this.startingPlayer = startingPlayer;
    }

    @Override
    public boolean resolve() {
        return true;
    }

}