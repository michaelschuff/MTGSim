package org.koi.gameobject.ability;

import org.koi.cost.Cost;
import org.koi.event.Event;
import org.koi.game.MTGGame;

import java.util.List;

public class ActivatedAbility extends Ability {
    public Cost cost;
    public List<Event> effects;
    public ActivatedAbility(MTGGame game, Cost cost, List<Event> effects) {
        super(game);
        this.cost = cost;
        this.effects = effects;
    }
}
