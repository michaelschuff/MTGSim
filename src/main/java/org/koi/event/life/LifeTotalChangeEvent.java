package org.koi.event.life;

import org.koi.event.Event;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.Player;

public abstract class LifeTotalChangeEvent extends Event {
    public Player player;
    public int amount;

    public LifeTotalChangeEvent(MTGGame game,
                                Card source,
                                Player player,
                                int amount) {
        super(game, source);
        this.player = player;
        this.amount = amount;

    }

}
