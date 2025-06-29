package org.koi.cost;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.Player;

public class TapSelfCostEvent extends CostEvent {
    public Card source;
    public TapSelfCostEvent(MTGGame game,
                            Card source) {
        super(game);
        this.source = source;
    }

    @Override
    public boolean canPay(Player player) {
        if (source.controller != player) return false;
        if (source.status.tapped) return false;
        if (!source.status.phasedIn) return false;
        return true;
    }

    @Override
    public void pay(Player player) {

    }

    @Override
    public boolean resolve() {
        pay(game.getPriorityPlayer());
        return true;
    }
}
