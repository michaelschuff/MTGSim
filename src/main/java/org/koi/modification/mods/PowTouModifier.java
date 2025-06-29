package org.koi.modification.mods;

import org.koi.game.MTGGame;
import org.koi.gameobject.PowTou;
import org.koi.modification.Modifier;

public abstract class PowTouModifier extends Modifier<PowTou> {
    private final MOD_TYPE mode;

    protected PowTouModifier(MOD_TYPE m) {
        this.mode = m;
    }

    @Override
    public PowTou apply(PowTou original) {
        PowTou value = this.getValue();
        switch (this.mode) {
            case OVERRIDE:
                return value;
            case ADD:
                return original + value;
            case SUBTRACT:
                return original - value;
            case SWAP:
                PowTou ret = new PowTou(original);
                ret.swap();
                return ret;
            default:
                throw new IllegalStateException("Unexpected value: " + this.mode);
        }
    }
}
