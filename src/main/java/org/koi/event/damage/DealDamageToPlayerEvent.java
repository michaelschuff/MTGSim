package org.koi.event.damage;

import org.koi.event.life.LifeLossCauser;
import org.koi.game.MTGGame;
import org.koi.util.GameObjectOrPlayer;
import org.koi.util.Player;

public class DealDamageToPlayerEvent extends DealDamageEvent implements LifeLossCauser {

    public DealDamageToPlayerEvent(MTGGame game,
                                   GameObjectOrPlayer source,
                                   Player target,
                                   int amount) {
        super(game, source, target.asGameObjectOrPlayer(), amount);
    }



    @Override
    public boolean resolve() {
        if (amount > 0) {
            // TODO: deal with protection and stuff
            target.player.data.lifeTotal -= amount;
            return true;
        }

        return false;
    }
}
