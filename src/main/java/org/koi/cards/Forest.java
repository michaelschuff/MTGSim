package org.koi.cards;

import org.koi.gameobject.cost.Cost;
import org.koi.gameobject.cost.ManaCostBuilder;
import org.koi.gameobject.cost.TapSelfCostEvent;
import org.koi.event.mana.AddManaEvent;
import org.koi.game.MTGGame;
import org.koi.gameobject.ability.ActivatedAbility;
import org.koi.gameobject.ability.OracleCardAbilitiesBuilder;
import org.koi.gameobject.card.OracleCard;
import org.koi.gameobject.typeline.TypelineBuilder;
import org.koi.gameobject.mana.Mana;
import org.koi.util.Color;

import java.util.List;

public abstract class Forest {
    public static OracleCard getCard(MTGGame game) {
        return new OracleCard(
                "Forest",
                0, 0,
                Color.COLORLESS,
                new TypelineBuilder().basic().land().forest().build(),
                new ManaCostBuilder().build(),
                new OracleCardAbilitiesBuilder().addActivatedAbility(
                        (card) -> new ActivatedAbility(
                                game,
                                card,
                                new Cost(
                                        List.of(
                                                new TapSelfCostEvent(
                                                        game,
                                                        card
                                                )
                                        )
                                ),
                                List.of(
                                        new AddManaEvent(
                                                game,
                                                card,
                                                card.controller,
                                                new Mana(Color.GREEN, card)
                                        )
                                )
                        )
                ).build()
        );
    }
}
