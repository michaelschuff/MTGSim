package org.koi.gameobject.cards;

import org.koi.util.Color;
import org.koi.gameobject.MTGCard;
import org.koi.mana.ManaCostBuilder;
import org.koi.mana.ManaSymbol;
import org.koi.gameobject.typeline.TypelineBuilder;

import java.util.List;

public abstract class OnakkeOgre {
    public static MTGCard getCard() {
        return new MTGCard(
                "Onakke Ogre",
                4, 2,
                Color.RED,
                new TypelineBuilder().creature().ogre().warrior().build(),
                new ManaCostBuilder().add(ManaSymbol.TWO).add(ManaSymbol.RED).build(),
                List.of(),
                0);
    }
}
