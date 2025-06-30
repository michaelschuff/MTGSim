package org.koi.gameobject.ability;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.modification.Modification;

import java.util.List;
import java.util.function.Function;

public abstract class StaticModifierAbility extends StaticAbility {
    public final Modification mod;

    public StaticModifierAbility(MTGGame game,
                                 Card source,
                                 Modification mod) {
        super(game, source);
        this.mod = mod;
    }

    public abstract List<Card> getSubjects();
}
