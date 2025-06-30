package org.koi.gameobject.ability;

import org.koi.gameobject.card.Card;
import org.koi.gameobject.cost.Cost;
import org.koi.event.Event;
import org.koi.game.MTGGame;

import java.util.List;

public class ActivatedAbility extends Ability {
    public Cost cost;
    public List<Event> effects;
    public ActivatedAbility(MTGGame game,
                            Card source,
                            Cost cost,
                            List<Event> effects) {
        super(game, source);
        this.cost = cost;
        this.effects = effects;
    }
}
