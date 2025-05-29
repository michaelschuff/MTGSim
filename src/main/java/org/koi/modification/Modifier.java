package org.koi.modification;

public abstract class Modifier<T> implements IModifier<T> {
    public enum MOD_TYPE {
        OVERRIDE,
        ADD,
        SUBTRACT,
        SWAP
    }

    protected abstract T getValue();

}
