package org.koi.cards;

import org.koi.game.MTGGame;
import org.koi.gameobject.ability.CardAbilitiesBuilder;
import org.koi.gameobject.ability.OracleCardAbilitiesBuilder;
import org.koi.util.Color;
import org.koi.gameobject.ability.StaticModifierAbility;
import org.koi.gameobject.card.OracleCard;
import org.koi.gameobject.PowTou;
import org.koi.cost.ManaCostBuilder;
import org.koi.mana.ManaSymbol;
import org.koi.modification.ModificationBuilder;
import org.koi.modification.Modifier;
import org.koi.modification.mods.PowTouModifier;
import org.koi.gameobject.typeline.TypelineBuilder;

import java.util.stream.Collectors;

public abstract class LegionsInitiative {
    public static OracleCard getCard(MTGGame game) {
        return new OracleCard(
                "Legions Initiative",
                0, 0,
                Color.BOROS,
                new TypelineBuilder().enchantment().build(),
                new ManaCostBuilder().add(ManaSymbol.WHITE).add(ManaSymbol.RED).build(),
                new OracleCardAbilitiesBuilder().addStaticAbility(
                        (self) -> new StaticModifierAbility(
                                game,
                                new ModificationBuilder(game).addPowTouMod(
                                        new PowTouModifier(Modifier.MOD_TYPE.ADD) {
                                            @Override
                                            protected PowTou getValue() {
                                                return new PowTou(1, 0);
                                            }
                                        }
                                ).build(),
                                (source) -> true,
                                (source) -> game.data.battlefield
                                        .stream()
                                        .filter((c) ->
                                                c.color.matches(Color.RED)
                                                && c.typeline.isType("Creature")
                                                && c.controller == source.controller)
                                        .collect(Collectors.toList())
                        )
                ).addStaticAbility(
                        (self) -> new StaticModifierAbility(
                                game,
                                new ModificationBuilder(game).addPowTouMod(
                                        new PowTouModifier(Modifier.MOD_TYPE.ADD) {
                                            @Override
                                            protected PowTou getValue() {
                                                return new PowTou(0, 1);
                                            }
                                        }
                                ).build(),
                                (source) -> true,
                                (source) -> game.data.battlefield
                                        .stream()
                                        .filter((c) ->
                                                c.color.matches(Color.WHITE)
                                                && c.typeline.isType("Creature")
                                                && c.controller == source.controller)
                                        .collect(Collectors.toList())
                        )
                ).build()
        );
    }
}
