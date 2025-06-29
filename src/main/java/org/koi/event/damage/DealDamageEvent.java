package org.koi.event.damage;

import org.koi.event.Event;
import org.koi.game.MTGGame;
import org.koi.util.GameObjectOrPlayer;

public abstract class DealDamageEvent extends Event {
    public GameObjectOrPlayer source;
    public GameObjectOrPlayer target;
    public int amount;
    public DealDamageEvent(MTGGame game,
                           GameObjectOrPlayer source,
                           GameObjectOrPlayer target,
                           int amount) {
        super(game, source);
        this.target = target;
        this.amount = amount;
        this.source = source;
    }
}
