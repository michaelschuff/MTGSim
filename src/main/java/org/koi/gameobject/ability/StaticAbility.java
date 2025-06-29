package org.koi.gameobject.ability;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;

import java.util.function.Function;

public abstract class StaticAbility extends Ability {
    public final Function<Card, Boolean> condition;
    public StaticAbility(MTGGame game,
                         Function<Card, Boolean> condition) {
        super(game);
        this.condition = condition;
    }
}
