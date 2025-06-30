package org.koi.cards;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.OracleCardAbilities;
import org.koi.util.Color;
import org.koi.gameobject.card.OracleCard;
import org.koi.gameobject.cost.ManaCostBuilder;
import org.koi.gameobject.mana.ManaSymbol;
import org.koi.gameobject.typeline.TypelineBuilder;

public abstract class OnakkeOgre {
    public static OracleCard getCard(MTGGame game) {
        return new OracleCard(
                "Onakke Ogre",
                4, 2,
                Color.RED,
                new TypelineBuilder().creature().ogre().warrior().build(),
                new ManaCostBuilder().add(ManaSymbol.TWO).add(ManaSymbol.RED).build(),
                new OracleCardAbilities()
        );
    }
}
