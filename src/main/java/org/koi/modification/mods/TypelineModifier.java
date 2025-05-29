package org.koi.modification.mods;

import org.koi.modification.Modifier;
import org.koi.gameobject.typeline.Typeline;

public abstract class TypelineModifier extends Modifier<Typeline> {
    private final MOD_TYPE mode;

    protected TypelineModifier(MOD_TYPE m) {
        this.mode = m;
    }

    @Override
    public Typeline apply(Typeline original) {
        Typeline value = this.getValue();
        switch (this.mode) {
            case OVERRIDE:
                return value;
            case ADD:
                return original | value;
            case SUBTRACT:
                return original - value;
            default:
                throw new IllegalStateException("Unexpected value: " + this.mode);
        }
    }
}
