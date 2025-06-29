package org.koi.event.cost;

import org.koi.cost.Cost;
import org.koi.cost.ManaCost;
import org.koi.cost.Payable;
import org.koi.event.Event;
import org.koi.game.MTGGame;
import org.koi.mana.ManaPool;
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
