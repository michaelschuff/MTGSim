package org.koi.modification.mods;

import org.koi.util.Color;
import org.koi.modification.Modifier;

public abstract class ColorModifier extends Modifier<Color> {
    private final MOD_TYPE mode;

    protected ColorModifier(MOD_TYPE m) {
        this.mode = m;
    }

    @Override
    public Color apply(Color original) {
        Color c = this.getValue();
        switch (this.mode) {
            case OVERRIDE:
                return c;
            case ADD:
                return original | c;
            case SUBTRACT:
                return original & ~c;
            default:
                throw new IllegalStateException("Unexpected value: " + this.mode);
        }
    }
}
