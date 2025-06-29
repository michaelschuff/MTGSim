package org.koi.event.damage;

import org.koi.event.Event;
import org.koi.game.MTGGame;
import org.koi.util.GameObjectOrPlayer;
import org.koi.util.Player;

public class DealOrdinaryDamageToPlayerEvent extends DealDamageToPlayerEvent {
    public DealOrdinaryDamageToPlayerEvent(MTGGame game,
                                           GameObjectOrPlayer source,
                                           Player target,
                                           int amount) {
        super(game, source, target, amount);
    }
}
