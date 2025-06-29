package org.koi.event.damage;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.GameObjectOrPlayer;

public class DealDamageToCardEvent extends DealDamageEvent {

    public DealDamageToCardEvent(MTGGame game,
                                 GameObjectOrPlayer source,
                                 Card target,
                                 int amount) {
        super(game, source, target.asGameObjectOrPlayer(), amount);
    }



    @Override
    public boolean resolve() {
        if (amount > 0) {
            // TODO: deal with protection and stuff
            if (target.o instanceof Card) {
                Card c = (Card) target.o;
                c.status.damage += amount;
            }
            return true;
        }

        return false;
    }
}
