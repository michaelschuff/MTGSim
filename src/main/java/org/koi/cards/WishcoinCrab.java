package org.koi.cards;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.CardAbilities;
import org.koi.gameobject.card.OracleCardAbilities;
import org.koi.util.Color;
import org.koi.gameobject.card.OracleCard;
import org.koi.cost.ManaCostBuilder;
import org.koi.mana.ManaSymbol;
import org.koi.gameobject.typeline.TypelineBuilder;

public abstract class WishcoinCrab {
    public static OracleCard getCard(MTGGame game) {
        return new OracleCard(
                "Wishcoin Crab",
                2, 5,
                Color.BLUE,
                new TypelineBuilder().creature().crab().build(),
                new ManaCostBuilder().add(ManaSymbol.THREE).add(ManaSymbol.BLUE).build(),
                new OracleCardAbilities()
        );
    }
}
