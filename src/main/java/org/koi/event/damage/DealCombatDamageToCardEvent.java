package org.koi.event.damage;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.GameObjectOrPlayer;

public class DealCombatDamageToCardEvent extends DealDamageToCardEvent {
    public DealCombatDamageToCardEvent(MTGGame game,
                                       GameObjectOrPlayer source,
                                       Card target,
                                       int amount) {
        super(game, source, target, amount);
    }
}
