package org.koi.event.mana;

import org.koi.event.Event;
import org.koi.game.MTGGame;
import org.koi.gameobject.mana.Mana;
import org.koi.util.Player;

import java.util.List;

public class ManaLeavesPoolEvent extends Event implements ManaLeavesPool {
    public Player player;
    public List<Mana> mana;
    public ManaLeavesPoolEvent(MTGGame game,
                               Player player,
                               List<Mana> mana) {
        super(game);
        this.player = player;
        this.mana = mana;
    }


    @Override
    public boolean resolve() {
        if (mana == null) {
            int initialSize = player.data.manaPool.size();
            player.data.manaPool.emptyOut(); //special empty out function
            int finalSize = player.data.manaPool.size();
            return initialSize != finalSize;
        } else {
            boolean success = true;
            for (Mana m : mana) {
                if (!player.data.manaPool.remove(m)) {
                    success = false;
                }
            }
            return success;
        }
    }
}
