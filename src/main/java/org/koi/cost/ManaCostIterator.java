package org.koi.cost;

import org.koi.mana.ManaSymbol;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ManaCostIterator implements Iterator<ManaSymbol> {
    private int currentIndex;
    private final List<ManaSymbol> list;

    public ManaCostIterator(List<ManaSymbol> list) {
        this.list = list;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < list.size();
    }

    @Override
    public ManaSymbol next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return list.get(currentIndex++);
    }
}
