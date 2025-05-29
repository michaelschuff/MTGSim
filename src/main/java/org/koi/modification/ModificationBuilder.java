package org.koi.modification;

import org.koi.LAYER;
import org.koi.gameobject.ability.Ability;
import org.koi.modification.mods.*;

public class ModificationBuilder {
    private LAYER lowestLayer = LAYER.L7d;
    // ==========================================================================================
    // Layer 1 - Copiable Value Changing Effects
    // ==========================================================================================

    // Layer 1a - Copiable Effects
    private CardModifier cloneModifier;

    // These only exist if "As ... enters" and "as ... is turned face up"
    private StringModifier cloneNameModifier;
    private ManaCostModifier cloneManaCostModifier;
    private ColorModifier cloneColorModifier;
    private TypelineModifier cloneTypelineModifier;
    //    AbilityModifier copiable_textModifier; <- how the fuck
    private PowTouModifier clonePowTouModifier;
    private IntModifier cloneLoyaltyModifier;
    private ListModifier<Ability> cloneAddAbilityModifier;
    private ListModifier<Ability> cloneRemoveAbilityModifier;
    private ListModifier<Ability> cloneCannotGainAbilityModifier;


    // Layer 1b - Face-down spells and permanents
//    CardModifier morphDefiner; // maybe we dont do face down stuff


    // Layer 1c - Characteristics become Copiable Values]

    // ==========================================================================================
    // Layer 2 - Control Changing Effects
    // ==========================================================================================
    private IntModifier controllerModifier;


    // ==========================================================================================
    // Layer 3 - Text Changing Effects (Ability Modifying Effects?)
    // ==========================================================================================
//    AbilityModifier textModifier; <- how the fuck

    // ==========================================================================================
    // Layer 4 - Type Changing Effects
    // ==========================================================================================
    private TypelineModifier typeModifier;

    // ==========================================================================================
    // Layer 5 - Color Changing Effects
    // ==========================================================================================
    private ColorModifier colorModifier;

    // ==========================================================================================
    // Layer 6 - Ability-Adding Effects, Keyword Counter effects, Ability-Removing Effects
    //           and can't have an ability Effects
    // ==========================================================================================

    //might need to be AbilityListModifier
    // for determining if abilities are equal.
    private ListModifier<Ability> addAbilityModifier;
    private ListModifier<Ability> keywordCounterAbilityModifier;
    private ListModifier<Ability> removeAbilityModifier;

    // doesn't modify ability list. Separate list that keeps track of what abilities a card can gain
    // add ability might depend on this.
    private ListModifier<Ability> cannotGainAbilityModifier;


    // ==========================================================================================
    // Layer 7 - Power/Toughness Changing Effects
    // ==========================================================================================

    // Layer 7a - Characteristic Defining Effects
    private PowTouModifier powerToughnessDefiner;

    // Layer 7b - Set to Specific Value Effects
    private PowTouModifier basePowerToughnessModifier;

    // Layer 7c - Generic Power/Toughness Modifier Effects
    private PowTouModifier powerToughnessModifier;

    // Layer 7d - Power/Toughness Swap Effects
    private PowTouModifier powerToughnessSwap;


    public ModificationBuilder addCloneMod(CardModifier m) {
        this.cloneModifier = m;
        lowestLayer = LAYER.L1a;
        return this;
    }
    public ModificationBuilder addCloneNameMod(StringModifier m) {
        this.cloneNameModifier = m;
        lowestLayer = LAYER.L1a;
        return this;
    }
    public ModificationBuilder addCloneManaCostMod(ManaCostModifier m) {
        this.cloneManaCostModifier = m;
        lowestLayer = LAYER.L1a;
        return this;
    }
    public ModificationBuilder addCloneColorMod(ColorModifier m) {
        this.cloneColorModifier = m;
        lowestLayer = LAYER.L1a;
        return this;
    }
    public ModificationBuilder addCloneTypelineMod(TypelineModifier m) {
        this.cloneTypelineModifier = m;
        lowestLayer = LAYER.L1a;
        return this;
    }
    public ModificationBuilder addClonePowTouMod(PowTouModifier m) {
        this.clonePowTouModifier = m;
        lowestLayer = LAYER.L1a;
        return this;
    }
    public ModificationBuilder addCloneLoyaltyMod(IntModifier m) {
        this.cloneLoyaltyModifier = m;
        lowestLayer = LAYER.L1a;
        return this;
    }
    public ModificationBuilder addCloneAddAbilityMod(ListModifier<Ability> m) {
        this.cloneAddAbilityModifier = m;
        lowestLayer = LAYER.L1a;
        return this;
    }
    public ModificationBuilder addCloneRemoveAbilityMod(ListModifier<Ability> m) {
        this.cloneRemoveAbilityModifier = m;
        lowestLayer = LAYER.L1a;
        return this;
    }
    public ModificationBuilder addCloneCannotGainAbilityMod(ListModifier<Ability> m) {
        this.cloneCannotGainAbilityModifier = m;
        lowestLayer = LAYER.L1a;
        return this;
    }
    public ModificationBuilder addControllerMod(IntModifier m) {
        this.controllerModifier = m;
        if (lowestLayer.ordinal() > LAYER.L2.ordinal())
            lowestLayer = LAYER.L2;
        return this;
    }
    public ModificationBuilder addTypelineMod(TypelineModifier m) {
        this.typeModifier = m;
        if (lowestLayer.ordinal() > LAYER.L4.ordinal())
            lowestLayer = LAYER.L4;
        return this;
    }
    public ModificationBuilder addColorMod(ColorModifier m) {
        this.colorModifier = m;
        if (lowestLayer.ordinal() > LAYER.L5.ordinal())
            lowestLayer = LAYER.L5;
        return this;
    }
    public ModificationBuilder addAddAbilityMod(ListModifier<Ability> m) {
        this.addAbilityModifier = m;
        if (lowestLayer.ordinal() > LAYER.L6.ordinal())
            lowestLayer = LAYER.L6;
        return this;
    }
    public ModificationBuilder addKeywordCounterAbilityMod(ListModifier<Ability> m) {
        this.keywordCounterAbilityModifier = m;
        if (lowestLayer.ordinal() > LAYER.L6.ordinal())
            lowestLayer = LAYER.L6;
        return this;
    }
    public ModificationBuilder addRemoveAbilityMod(ListModifier<Ability> m) {
        this.removeAbilityModifier = m;
        if (lowestLayer.ordinal() > LAYER.L6.ordinal())
            lowestLayer = LAYER.L6;
        return this;
    }
    public ModificationBuilder addCannotGainAbilityMod(ListModifier<Ability> m) {
        this.cannotGainAbilityModifier = m;
        if (lowestLayer.ordinal() > LAYER.L6.ordinal())
            lowestLayer = LAYER.L6;
        return this;
    }

    public ModificationBuilder addCharacteristicPowTouMod(PowTouModifier m) {
        this.powerToughnessDefiner = m;
        if (lowestLayer.ordinal() > LAYER.L7a.ordinal())
            lowestLayer = LAYER.L7a;
        return this;
    }
    public ModificationBuilder addBasePowTouMod(PowTouModifier m) {
        this.basePowerToughnessModifier = m;
        if (lowestLayer.ordinal() > LAYER.L7b.ordinal())
            lowestLayer = LAYER.L7b;
        return this;
    }
    public ModificationBuilder addPowTouMod(PowTouModifier m) {
        this.powerToughnessModifier = m;
        if (lowestLayer.ordinal() > LAYER.L7c.ordinal())
            lowestLayer = LAYER.L7c;
        return this;
    }
    public ModificationBuilder addSwapPowTouMod(PowTouModifier m) {
        this.powerToughnessSwap = m;
        if (lowestLayer.ordinal() > LAYER.L7d.ordinal())
            lowestLayer = LAYER.L7d;
        return this;
    }
    public Modification build() {
        return new Modification(
                cloneModifier,
                cloneNameModifier,
                cloneManaCostModifier,
                cloneColorModifier,
                cloneTypelineModifier,
                clonePowTouModifier,
                cloneLoyaltyModifier,
                cloneAddAbilityModifier,
                cloneRemoveAbilityModifier,
                cloneCannotGainAbilityModifier,
                controllerModifier,
                typeModifier,
                colorModifier,
                addAbilityModifier,
                keywordCounterAbilityModifier,
                removeAbilityModifier,
                cannotGainAbilityModifier,
                powerToughnessDefiner,
                basePowerToughnessModifier,
                powerToughnessModifier,
                powerToughnessSwap,
                lowestLayer
                );
    }
}
