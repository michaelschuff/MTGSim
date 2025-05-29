package org.koi.modification;

public interface IModifier<T> {
    T apply(T original);
}
