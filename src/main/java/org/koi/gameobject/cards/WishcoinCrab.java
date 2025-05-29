package org.koi.gameobject.cards;

import org.koi.util.Color;
import org.koi.gameobject.MTGCard;
import org.koi.mana.ManaCostBuilder;
import org.koi.mana.ManaSymbol;
import org.koi.gameobject.typeline.TypelineBuilder;

import java.util.List;

public abstract class WishcoinCrab {
    public static MTGCard getCard() {
        return new MTGCard(
                "Wishcoin Crab",
                2, 5,
                Color.BLUE,
                new TypelineBuilder().creature().crab().build(),
                new ManaCostBuilder().add(ManaSymbol.THREE).add(ManaSymbol.BLUE).build(),
                List.of(),
                0
        );
    }
}
