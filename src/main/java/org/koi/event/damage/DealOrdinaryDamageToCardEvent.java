package org.koi.event.damage;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.GameObjectOrPlayer;

public class DealOrdinaryDamageToCardEvent extends DealDamageToCardEvent {
    public DealOrdinaryDamageToCardEvent(MTGGame game,
                                         GameObjectOrPlayer source,
                                         Card target,
                                         int amount) {
        super(game, source, target, amount);
    }
}
