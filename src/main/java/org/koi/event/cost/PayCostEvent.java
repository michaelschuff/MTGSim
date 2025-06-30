package org.koi.event.cost;

import org.koi.gameobject.cost.Cost;
import org.koi.gameobject.cost.ManaCost;
import org.koi.gameobject.cost.Payable;
import org.koi.event.Event;
import org.koi.game.MTGGame;
import org.koi.gameobject.mana.ManaPool;
import org.koi.util.Player;

public class PayCostEvent extends Event {
    public Player player;
    public Cost costs;
    public PayCostEvent(MTGGame game,
                        Player player,
                        Cost costs) {
        super(game);
        this.player = player;
        this.costs = costs;
    }

    @Override
    public boolean resolve() {
        for (Payable cost : costs) {
            if (cost instanceof ManaCost) {
                ManaPool mp = player.data.manaPool.canAfford((ManaCost) cost);

                if (mp == null) {
                    return false;
                }
                player.data.manaPool = mp;
            }
        }
        // TODO: implement other costs
        return true;
    }
}
