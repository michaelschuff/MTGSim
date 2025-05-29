package org.koi.gameobject.cards;

import org.koi.MTGGame;
import org.koi.util.Color;
import org.koi.gameobject.ability.StaticModifierAbility;
import org.koi.gameobject.MTGCard;
import org.koi.gameobject.PowTou;
import org.koi.mana.ManaCostBuilder;
import org.koi.mana.ManaSymbol;
import org.koi.modification.ModificationBuilder;
import org.koi.modification.Modifier;
import org.koi.modification.mods.PowTouModifier;
import org.koi.gameobject.typeline.TypelineBuilder;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class LegionsInitiative {
    public static MTGCard getCard() {
        return new MTGCard(
                "Legions Initiative",
                0, 0,
                Color.BOROS,
                new TypelineBuilder().enchantment().build(),
                new ManaCostBuilder().add(ManaSymbol.WHITE).add(ManaSymbol.RED).build(),
                Arrays.asList(
                        new StaticModifierAbility(
                                new ModificationBuilder().addPowTouMod(
                                        new PowTouModifier(Modifier.MOD_TYPE.ADD) {
                                            @Override
                                            protected PowTou getValue() {
                                                return new PowTou(1, 0);
                                            }
                                        }
                                ).build(),
                                (controller) -> true,
                                (controller) -> MTGGame.data().battlefield
                                        .stream()
                                        .filter((id) ->
                                                MTGGame.data().getCard(id).color.matches(Color.RED)
                                                && MTGGame.data().getCard(id).typeline.isType("Creature")
                                                && MTGGame.data().getCard(id).controller == controller)
                                        .collect(Collectors.toList())
                        ),
                        new StaticModifierAbility(
                                new ModificationBuilder().addPowTouMod(
                                        new PowTouModifier(Modifier.MOD_TYPE.ADD) {
                                            @Override
                                            protected PowTou getValue() {
                                                return new PowTou(0, 1);
                                            }
                                        }
                                ).build(),
                                (controller) -> true,
                                (controller) -> MTGGame.data().battlefield
                                        .stream()
                                        .filter((id) ->
                                                MTGGame.data().getCard(id).color.matches(Color.WHITE)
                                                && MTGGame.data().getCard(id).typeline.isType("Creature")
                                                && MTGGame.data().getCard(id).controller == controller)
                                        .collect(Collectors.toList())
                        )
                ),
                0);
    }
}
