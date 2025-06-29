package org.koi.gameobject.ability;


import org.koi.event.Event;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;

import java.util.function.Function;

public class StaticReplacementAbility extends StaticAbility {
    public Class<Event> originalType;
    public Event replacement;
    public StaticReplacementAbility(MTGGame game,
                                    Class<Event> originalType,
                                    Event replacement,
                                    Function<Card, Boolean> condition) {
        super(game, condition);
        this.originalType = originalType;
        this.replacement = replacement;
    }
}
