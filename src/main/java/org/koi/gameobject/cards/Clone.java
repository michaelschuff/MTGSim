package org.koi.gameobject.cards;

import org.koi.GameData;
import org.koi.MTGGame;
import org.koi.util.Color;
import org.koi.gameobject.ability.StaticModifierAbility;
import org.koi.gameobject.Card;
import org.koi.gameobject.MTGCard;
import org.koi.mana.ManaCostBuilder;
import org.koi.mana.ManaSymbol;
import org.koi.modification.ModificationBuilder;
import org.koi.modification.mods.CardModifier;
import org.koi.gameobject.typeline.TypelineBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Clone {
    public static MTGCard getCard(Card copy) {
        return new MTGCard(
                "Clone",
                0, 0,
                Color.BLUE,
                new TypelineBuilder().creature().shapeshifter().build(),
                new ManaCostBuilder().add(ManaSymbol.THREE).add(ManaSymbol.BLUE).build(),
                List.of(
                        new StaticModifierAbility(
                                new ModificationBuilder().addCloneMod(new CardModifier() {
                                    @Override
                                    protected Card getValue() {
                                        return copy;
                                    }
                                }).build(),
                                (controller) -> true,
                                (controller) -> {
                                    GameData g = MTGGame.getInstance().gameData;
                                    return g.battlefield
                                            .stream()
                                            .filter((id) -> g.getCard(id).name.equals("Clone"))
                                            .collect(Collectors.toList());
                                }

                        )
                ),
                0);
    }
}
