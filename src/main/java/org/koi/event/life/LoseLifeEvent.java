package org.koi.event.life;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.Player;

public class LoseLifeEvent extends LifeTotalChangeEvent implements LifeLossCauser {
    public LoseLifeEvent(MTGGame game,
                         Card source,
                         Player player,
                         int amount) {
        super(game, source, player, amount);
    }


    @Override
    public boolean resolve() {
        if (amount > 0) {
            player.data.lifeTotal += amount;
            return true;
        }
        return false;
    }
}
