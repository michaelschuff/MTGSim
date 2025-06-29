package org.koi.gameobject.ability;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.modification.Modification;

import java.util.List;
import java.util.function.Function;

public class StaticModifierAbility extends StaticAbility {
    public final Modification mod;
    public final Function<Card, List<Card>> filter;

    public StaticModifierAbility(MTGGame game,
                                 Modification mod,
                                 Function<Card, Boolean> condition,
                                 Function<Card, List<Card>> filter) {
        super(game, condition);
        this.mod = mod;
        this.filter = filter;
    }
}
