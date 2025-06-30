package org.koi.gameobject.cost;

import org.koi.event.Event;
import org.koi.game.MTGGame;

public abstract class CostEvent extends Event implements Payable {
    public CostEvent(MTGGame game) {
        super(game);
    }
}
