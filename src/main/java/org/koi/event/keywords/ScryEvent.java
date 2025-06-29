package org.koi.event.keywords;

import org.koi.event.Event;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.Player;

public class ScryEvent extends Event {
    public Player target;
    public int amount;
    public ScryEvent(MTGGame game,
                     Card source,
                     Player target,
                     int amount) {
        super(game, source);
        this.target = target;
        this.amount = amount;
    }

    @Override
    public boolean resolve() {
        if (amount <= 0)
            return false;
        // TODO: implement
        return true;
    }
}
