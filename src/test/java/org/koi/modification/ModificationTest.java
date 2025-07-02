package org.koi.modification;

import org.junit.Test;
import org.koi.LAYER;
import org.koi.cards.CardLoader;
import org.koi.game.MTGGame;
import org.koi.gameobject.PowTou;
import org.koi.gameobject.ability.Ability;
import org.koi.gameobject.ability.CardAbilitiesBuilder;
import org.koi.gameobject.card.Card;
import org.koi.gameobject.card.CardAbilities;
import org.koi.gameobject.typeline.Typeline;
import org.koi.gameobject.typeline.TypelineBuilder;
import org.koi.modification.mods.*;
import org.koi.util.Color;
import org.koi.util.Variant;

import java.util.List;

import static org.junit.Assert.*;

public class ModificationTest {

    @Test
    public void firstWorksOnLayer() {
        MTGGame game = new MTGGame(Variant.Commander);
        Card c = new Card(
                CardLoader.loadCard(game, "Forest"),
                game.startingPlayer,
                0
        );
        Modification m = new ModificationBuilder(game)
                .addColorMod(new ColorModifier(Modifier.MOD_TYPE.OVERRIDE) {
                    @Override
                    protected Color getValue() {
                        return Color.ABZAN;
                    }
                }).addCloneMod(new CardModifier() {
                    @Override
                    protected Card getValue() {
                        return c;
                    }
                }).build();

        assert m.firstWorksOnLayer(LAYER.L1a);
    }

    @Test
    public void apply() {
        MTGGame game = new MTGGame(Variant.Commander);
        game.loadDecks(List.of(List.of(),List.of(),List.of(),List.of()));
        Card original = new Card(
                CardLoader.loadCard(game, "Forest"),
                game.startingPlayer,
                0
        );
        Card copySource = new Card(
                CardLoader.loadCard(game, "OnakkeOgre"),
                game.startingPlayer,
                0
        );

        // forest's add green mana ability
        CardAbilities addGAbility = new CardAbilitiesBuilder().addAbility(original.abilities.activatedAbilities.get(0)).build();

        Modification m = new ModificationBuilder(game)
                .addCloneMod(new CardModifier() {
                    @Override
                    protected Card getValue() {
                        return copySource;
                    }
                })
                .addCloneNameMod(new StringModifier() {
                    @Override
                    protected String getValue() {
                        return "inexplicable";
                    }
                })
                .addControllerMod(new IntModifier(Modifier.MOD_TYPE.OVERRIDE) {
                    @Override
                    protected Integer getValue() {
                        return 3;
                    }
                })
                .addTypelineMod(new TypelineModifier(Modifier.MOD_TYPE.ADD) {
                    @Override
                    protected Typeline getValue() {
                        return new TypelineBuilder().legendary().build();
                    }
                })
                .addColorMod(new ColorModifier(Modifier.MOD_TYPE.OVERRIDE) {
                    @Override
                    protected Color getValue() {
                        return Color.COLORLESS;
                    }
                })
                .addAddAbilityMod(new CardAbilitiesModifier(Modifier.MOD_TYPE.ADD) {
                    @Override
                    protected CardAbilities getValue() {
                        return addGAbility;
                    }
                })
                .addCharacteristicPowTouMod(new PowTouModifier(Modifier.MOD_TYPE.OVERRIDE) {
                    @Override
                    protected PowTou getValue() {
                        return new PowTou(10,10);
                    }
                })
                .addBasePowTouMod(new PowTouModifier(Modifier.MOD_TYPE.ADD) {
                    @Override
                    protected PowTou getValue() {
                        return new PowTou(3, 4);
                    }
                })
                .addBasePowTouMod(new PowTouModifier(Modifier.MOD_TYPE.ADD) {
                    @Override
                    protected PowTou getValue() {
                        return new PowTou(3, 4);
                    }
                })
                .addPowTouMod(new PowTouModifier(Modifier.MOD_TYPE.ADD) {
                    @Override
                    protected PowTou getValue() {
                        return new PowTou(1,3);
                    }
                })
                // doesnt matter what we put,we dont use the value
                .addSwapPowTouMod(new PowTouModifier(Modifier.MOD_TYPE.SWAP) {
                    @Override
                    protected PowTou getValue() {
                        return null;
                    }
                })
                .build();

        Card card1a  = m.apply(original, LAYER.L1a);

        assert card1a.pt.power() == 4;
        assert card1a.pt.toughness() == 2;
        // TODO: implement .equals for typeline
        //       additionally, be able to iterate through its types
        assert card1a.typeline.isType("Ogre");
        assert card1a.abilities.size() == 0;
        assert card1a.color == Color.RED;
        // TODO: implement manaCost .equals
        assert card1a.manaCost.cmc() == 3;
        assert card1a.id.equals(original.id);
        assert card1a.name.equals("inexplicable");

        Card card1b = m.apply(card1a, LAYER.L1b);
        //TODO: implement morph modifier

        Card card1c = m.apply(card1b, LAYER.L1c);
        // TODO: not implemented yet
        Card card2 = m.apply(card1c, LAYER.L2 );
        assert card2.controller.index == 3;
        Card card3 = m.apply(card2, LAYER.L3 );
        // TODO implement text changing
        Card card4 = m.apply(card3, LAYER.L4 );
        assert card4.typeline.isType("Legendary");
        Card card5 = m.apply(card4, LAYER.L5 );
        assert card5.color.matches(Color.COLORLESS);
        Card card6 = m.apply(card5, LAYER.L6 );
        assert card6.abilities.size() == 1;
        //TODO modifier cannot set negative power (maybe not for characteristic defining)
        Card card7a = m.apply(card6, LAYER.L7a);
        assert card7a.pt.power() == 10;
        assert card7a.pt.toughness() == 10;
        Card card7b = m.apply(card7a, LAYER.L7b);
        assert card7b.pt.power() == 13;
        assert card7b.pt.toughness() == 14;
        Card card7c = m.apply(card7b, LAYER.L7c);
        assert card7c.pt.power() == 14;
        assert card7c.pt.toughness() == 17;
        Card card7d = m.apply(card7c, LAYER.L7d);
        assert card7d.pt.power() == 17;
        assert card7d.pt.toughness() == 14;
    }
}