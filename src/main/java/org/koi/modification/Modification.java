package org.koi.modification;


import jdk.jshell.spi.ExecutionControl;
import org.koi.LAYER;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.modification.mods.*;

public class Modification {
    public final MTGGame game;
    public final LAYER lowestLayer;
    // ==========================================================================================
    // Layer 1 - Copiable Value Changing Effects
    // ==========================================================================================
    // Layer 1a - Copiable Effects
    public final CardModifier cloneModifier;

    // These only exist if "As ... enters" and "as ... is turned face up"
    public final StringModifier cloneNameModifier;
    public final ManaCostModifier cloneManaCostModifier;
    public final ColorModifier cloneColorModifier;
    public final TypelineModifier cloneTypelineModifier;
//    AbilityModifier copiable_textModifier; <- how the fuck
    public final PowTouModifier clonePowTouModifier;
    public final IntModifier cloneLoyaltyModifier;
    public final CardAbilitiesModifier cloneAddAbilityModifier;
    public final CardAbilitiesModifier cloneRemoveAbilityModifier;
    public final CardAbilitiesModifier cloneCannotGainAbilityModifier;


    // Layer 1b - Face-down spells and permanents
    //TODO: add face down stuff (cr 708.2)
//    CardModifier morphDefiner;


    // Layer 1c - Characteristics become Copiable Values (not a real layer, but might be needed)

    // ==========================================================================================
    // Layer 2 - Control Changing Effects
    // ==========================================================================================
    public final IntModifier controllerModifier;


    // ==========================================================================================
    // Layer 3 - Text Changing Effects (Ability Modifying Effects?)
    // ==========================================================================================
    // TODO: implement
//    AbilityModifier textModifier;

    // ==========================================================================================
    // Layer 4 - Type Changing Effects
    // ==========================================================================================
    public final TypelineModifier typeModifier;

    // ==========================================================================================
    // Layer 5 - Color Changing Effects
    // ==========================================================================================
    public final ColorModifier colorModifier;

    // ==========================================================================================
    // Layer 6 - Ability-Adding Effects, Keyword Counter effects, Ability-Removing Effects
    //           and can't have an ability Effects
    // ==========================================================================================

    //might need to be AbilityListModifier
    // for determining if abilities are equal.
    public final CardAbilitiesModifier addAbilityModifier;
    public final CardAbilitiesModifier keywordCounterAbilityModifier;
    public final CardAbilitiesModifier removeAbilityModifier;

    // doesn't modify ability list. Separate list that keeps track of what abilities a card can gain
    // add ability might depend on this.
    public final CardAbilitiesModifier cannotGainAbilityModifier;


    // ==========================================================================================
    // Layer 7 - Power/Toughness Changing Effects
    // ==========================================================================================

    // Layer 7a - Characteristic Defining Effects
    public final PowTouModifier powerToughnessDefiner;

    // Layer 7b - Set to Specific Value Effects
    public final PowTouModifier basePowerToughnessModifier;

    // Layer 7c - Generic Power/Toughness Modifier Effects
    public final PowTouModifier powerToughnessModifier;

    // Layer 7d - Power/Toughness Swap Effects
    public final PowTouModifier powerToughnessSwap;

    //TODO: add layer 8 which is all other effects, such as extra land drop or increase max hand size



    public boolean firstWorksOnLayer(LAYER l) {
        return l.ordinal() == lowestLayer.ordinal();
    }


    //TODO: i dont think this needs to return card, just have it modify c in place
    public Card apply(Card c, LAYER l) {
        switch (l) {
            case L1a:
                if (cloneModifier != null)
                    this.cloneModifier.apply(c);
                if (cloneNameModifier != null)
                    c.name = this.cloneNameModifier.apply(c.name);
                if (cloneManaCostModifier != null)
                    c.manaCost = this.cloneManaCostModifier.apply(c.manaCost);
                if (cloneColorModifier != null)
                    c.color = this.cloneColorModifier.apply(c.color);
                if (cloneTypelineModifier != null)
                    c.typeline = this.cloneTypelineModifier.apply(c.typeline);
                if (clonePowTouModifier != null)
                    c.pt = this.clonePowTouModifier.apply(c.pt);
                //TODO: implement loyalty modifier, should use CounterStore
//                if (cloneLoyaltyModifier != null)
//                    c.loyalty = this.cloneLoyaltyModifier.apply(c.loyalty);
                if (cloneAddAbilityModifier != null)
                    c.abilities = this.cloneAddAbilityModifier.apply(c.abilities);
                if (cloneRemoveAbilityModifier != null)
                    c.abilities = this.cloneRemoveAbilityModifier.apply(c.abilities);
                // TODO: implement. need some way of comparing abilities
//                if (cloneCannotGainAbilityModifier != null)
//                    c.bannedAbilities = this.cloneCannotGainAbilityModifier.apply(c.bannedAbilities);
                return c;

            case L1b:
                //TODO: apply face down modifier (cr 708.2)
                return c;
            case L1c:
                //TODO: set copiable values as characteristic
                return c;
            case L2:
                if (controllerModifier != null)
                    c.controller = game.data.players.get(this.controllerModifier.apply(c.controller.index));
                return c;
            case L3:
                return c;
            case L4:
                if (typeModifier != null)
                    c.typeline = this.typeModifier.apply(c.typeline);
                return c;
            case L5:
                if (colorModifier != null)
                    c.color = this.colorModifier.apply(c.color);
                return c;
            case L6:
                if (addAbilityModifier != null)
                    c.abilities = this.addAbilityModifier.apply(c.abilities);
                if (keywordCounterAbilityModifier != null)
                    c.abilities = this.keywordCounterAbilityModifier.apply(c.abilities);
                if (removeAbilityModifier != null)
                    c.abilities = this.removeAbilityModifier.apply(c.abilities);
//                if (cannotGainAbilityModifier != null)
//                    c.bannedAbilities = this.cannotGainAbilityModifier.apply(c.bannedAbilities);
                return c;
            case L7a:
                if (powerToughnessDefiner != null)
                    c.pt = powerToughnessDefiner.apply(c.pt);
                return c;
            case L7b:
                if (basePowerToughnessModifier != null)
                    c.pt = basePowerToughnessModifier.apply(c.pt);
                return c;
            case L7c:
                if (powerToughnessModifier != null)
                    c.pt = powerToughnessModifier.apply(c.pt);
                return c;
            case L7d:
                if (powerToughnessSwap != null)
                    c.pt = powerToughnessSwap.apply(c.pt);
                return c;
            default:
                return c;
        }
    }


    public Modification(MTGGame game,
                        CardModifier cloneModifier,
                        StringModifier cloneNameModifier,
                        ManaCostModifier cloneManaCostModifier,
                        ColorModifier cloneColorModifier,
                        TypelineModifier cloneTypelineModifier,
                        PowTouModifier clonePowTouModifier,
                        IntModifier cloneLoyaltyModifier,
                        CardAbilitiesModifier cloneAddAbilityModifier,
                        CardAbilitiesModifier cloneRemoveAbilityModifier,
                        CardAbilitiesModifier cloneCannotGainAbilityModifier,
                        IntModifier controllerModifier,
                        TypelineModifier typeModifier,
                        ColorModifier colorModifier,
                        CardAbilitiesModifier addAbilityModifier,
                        CardAbilitiesModifier keywordCounterAbilityModifier,
                        CardAbilitiesModifier removeAbilityModifier,
                        CardAbilitiesModifier cannotGainAbilityModifier,
                        PowTouModifier powerToughnessDefiner,
                        PowTouModifier basePowerToughnessModifier,
                        PowTouModifier powerToughnessModifier,
                        PowTouModifier powerToughnessSwap,
                        LAYER lowestLayer) {
        this.game = game;
        this.cloneModifier = cloneModifier;
        this.cloneNameModifier = cloneNameModifier;
        this.cloneManaCostModifier = cloneManaCostModifier;
        this.cloneColorModifier = cloneColorModifier;
        this.cloneTypelineModifier = cloneTypelineModifier;
        this.clonePowTouModifier = clonePowTouModifier;
        this.cloneLoyaltyModifier = cloneLoyaltyModifier;
        this.cloneAddAbilityModifier = cloneAddAbilityModifier;
        this.cloneRemoveAbilityModifier = cloneRemoveAbilityModifier;
        this.cloneCannotGainAbilityModifier = cloneCannotGainAbilityModifier;
        this.controllerModifier = controllerModifier;
        this.typeModifier = typeModifier;
        this.colorModifier = colorModifier;
        this.addAbilityModifier = addAbilityModifier;
        this.keywordCounterAbilityModifier = keywordCounterAbilityModifier;
        this.removeAbilityModifier = removeAbilityModifier;
        this.cannotGainAbilityModifier = cannotGainAbilityModifier;
        this.powerToughnessDefiner = powerToughnessDefiner;
        this.basePowerToughnessModifier = basePowerToughnessModifier;
        this.powerToughnessModifier = powerToughnessModifier;
        this.powerToughnessSwap = powerToughnessSwap;
        this.lowestLayer = lowestLayer;
    }
}
