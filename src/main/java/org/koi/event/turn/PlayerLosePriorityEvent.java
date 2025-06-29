package org.koi.event.turn;

import org.koi.event.Event;
import org.koi.game.MTGGame;
import org.koi.util.Player;

public class PlayerLosePriorityEvent extends TemporalEvent {
    public Player player;
    public PlayerLosePriorityEvent(MTGGame game, Player player) {
        super(game);
        this.player = player;
    }

    @Override
    public boolean resolve() {
        return true;
    }
}
