package org.koi.event.damage;

import org.koi.event.Event;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;

public class RemoveMarkedDamageEvent extends Event {
    public Card c;
    public int amount;

    // -1 amount for remove all damage
    public RemoveMarkedDamageEvent(MTGGame game,
                                   Card c,
                                   int amount) {
        super(game);
        this.c = c;
        this.amount = amount;
    }
    @Override
    public boolean resolve() {
        if (amount == -1) {
            this.c.status.damage = 0;
        } else {
            this.c.status.damage -= amount;
        }
        return false;
    }
}
