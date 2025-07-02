package org.koi.modification;

import org.koi.game.MTGGame;

public abstract class Modifier<T> implements IModifier<T> {
    public enum MOD_TYPE {
        OVERRIDE,
        ADD,
        SUBTRACT,
        SWAP
    }

    protected abstract T getValue();

}
