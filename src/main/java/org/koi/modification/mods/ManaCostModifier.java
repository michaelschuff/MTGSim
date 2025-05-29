package org.koi.modification.mods;

import org.koi.mana.ManaCost;
import org.koi.mana.ManaCostBuilder;
import org.koi.modification.Modifier;

public abstract class ManaCostModifier extends Modifier<ManaCost> {
    private final MOD_TYPE mode;

    protected ManaCostModifier(ManaCost value, MOD_TYPE m) {
        this.mode  = m;
    }

    @Override
    public ManaCost apply(ManaCost original) {
        ManaCost value = this.getValue();
        switch (this.mode) {
            case OVERRIDE:
                return value;
            case ADD:
                return new ManaCostBuilder(original).add(value).build();
            case SUBTRACT:
                return new ManaCostBuilder(original).add(-value).build();
            default:
                throw new IllegalStateException("Unexpected value: " + this.mode);
        }
    }
}
