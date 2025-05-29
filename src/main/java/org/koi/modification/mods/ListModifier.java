package org.koi.modification.mods;

import org.koi.modification.Modifier;

import java.util.ArrayList;
import java.util.List;

public abstract class ListModifier<T> extends Modifier<List<T>> {
    private final MOD_TYPE mode;

    protected ListModifier(MOD_TYPE m) {
        this.mode = m;
    }
    @Override
    public List<T> apply(List<T> original) {
        List<T> v = this.getValue();
        switch (this.mode) {
            case OVERRIDE: {
                return v;
            }
            case ADD: {
                ArrayList<T> ret = new ArrayList<>(original);
                ret.addAll(v);
                return ret;
            }
            case SUBTRACT: {
                ArrayList<T> ret = new ArrayList<>(original);
                ret.removeAll(v);
                return ret;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + this.mode);
        }
    }
}
