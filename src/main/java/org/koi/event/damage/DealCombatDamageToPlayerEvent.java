package org.koi.event.damage;

import org.koi.game.MTGGame;
import org.koi.util.GameObjectOrPlayer;
import org.koi.util.Player;

public class DealCombatDamageToPlayerEvent extends DealDamageToPlayerEvent {
    public DealCombatDamageToPlayerEvent(MTGGame game,
                                         GameObjectOrPlayer source,
                                         Player target,
                                         int amount) {
        super(game, source, target, amount);
    }
}