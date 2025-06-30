package org.koi.gameobject.ability;


import org.koi.event.Event;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;

import java.util.List;
import java.util.function.Function;


// TODO: This is kinda similar to Modification<List<Event>>....
public abstract class StaticReplacementAbility extends StaticAbility {
    public StaticReplacementAbility(MTGGame game, Card source) {
        super(game, source);
    }

    public abstract boolean isActiveCondition();
    public abstract boolean shouldReplace(List<Event> events);
    public abstract List<Event> replace(List<Event> events);
}
