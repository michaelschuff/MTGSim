package org.koi.modification.mods;

import org.koi.modification.Modifier;

public abstract class IntModifier extends Modifier<Integer> {
    private final MOD_TYPE mode;

    protected IntModifier(MOD_TYPE m) {
        this.mode = m;
    }

    @Override
    public Integer apply(Integer original) {
        int v = this.getValue();
        switch (this.mode) {
            case OVERRIDE:
                return v;
            case ADD:
                return original + v;
            case SUBTRACT:
                return original - v;
            default:
                throw new IllegalStateException("Unexpected value: " + this.mode);
        }
    }
}
