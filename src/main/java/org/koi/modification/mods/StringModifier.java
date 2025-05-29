package org.koi.modification.mods;

import org.koi.modification.Modifier;

public abstract class StringModifier extends Modifier<String> {

    protected StringModifier() { }

    @Override
    public String apply(String original) {
        return this.getValue();
    }
}
