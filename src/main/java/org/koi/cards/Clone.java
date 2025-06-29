package org.koi.cards;

import org.koi.game.MTGGame;
import org.koi.gameobject.ability.CardAbilitiesBuilder;
import org.koi.gameobject.ability.OracleCardAbilitiesBuilder;
import org.koi.util.Color;
import org.koi.gameobject.ability.StaticModifierAbility;
import org.koi.gameobject.card.Card;
import org.koi.gameobject.card.OracleCard;
import org.koi.cost.ManaCostBuilder;
import org.koi.mana.ManaSymbol;
import org.koi.modification.ModificationBuilder;
import org.koi.modification.mods.CardModifier;
import org.koi.gameobject.typeline.TypelineBuilder;

import java.util.stream.Collectors;

public abstract class Clone {
    public static OracleCard getCard(MTGGame game) {
        return new OracleCard(
                "Clone",
                0, 0,
                Color.BLUE,
                new TypelineBuilder().creature().shapeshifter().build(),
                new ManaCostBuilder().add(ManaSymbol.THREE).add(ManaSymbol.BLUE).build(),
                new OracleCardAbilitiesBuilder().addStaticAbility(
                        (self) -> new StaticModifierAbility(
                                game,
                                new ModificationBuilder(game).addCloneMod(new CardModifier() {
                                    @Override
                                    protected Card getValue() {
                                        // TODO: fix
                                        return null;
                                    }
                                }).build(),
                                (source) -> true,
                                (source) -> {
                                    return game.data.battlefield
                                            .stream()
                                            .filter((c) -> c.name.equals("Clone"))
                                            .collect(Collectors.toList());
                                }

                        )
                ).build()
        );
    }
}
