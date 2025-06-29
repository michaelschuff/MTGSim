package org.koi.cards;

import org.koi.cost.Cost;
import org.koi.cost.ManaCostBuilder;
import org.koi.cost.TapSelfCostEvent;
import org.koi.event.mana.AddManaEvent;
import org.koi.game.MTGGame;
import org.koi.gameobject.ability.ActivatedAbility;
import org.koi.gameobject.ability.OracleCardAbilitiesBuilder;
import org.koi.gameobject.card.OracleCard;
import org.koi.gameobject.typeline.TypelineBuilder;
import org.koi.mana.Mana;
import org.koi.util.Color;

import java.util.List;

public abstract class Plains {
    public static OracleCard getCard(MTGGame game) {
        return new OracleCard(
                "Plains",
                0, 0,
                Color.COLORLESS,
                new TypelineBuilder().basic().land().plains().build(),
                new ManaCostBuilder().build(),
                new OracleCardAbilitiesBuilder().addActivatedAbility(
                        (self) -> new ActivatedAbility(
                                game,
                                new Cost(
                                        List.of(
                                                new TapSelfCostEvent(
                                                        game,
                                                        self
                                                )
                                        )
                                ),
                                List.of(
                                        new AddManaEvent(
                                                game,
                                                self,
                                                self.controller,
                                                new Mana(Color.WHITE, self)
                                        )
                                )
                        )
                ).build()
        );
    }
}
