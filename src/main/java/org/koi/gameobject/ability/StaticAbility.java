package org.koi.gameobject.ability;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;

import java.util.function.Function;

public abstract class StaticAbility extends Ability {
    public StaticAbility(MTGGame game,
                         Card source) {
        super(game, source);
    }

    public abstract boolean conditionalSuffix();
}
