package org.koi.gameobject.mana;

import org.koi.gameobject.card.Card;
import org.koi.util.Color;

import java.util.function.Function;

public class Mana {
    public Color color;
    public Card source;
    public Function<Object, Boolean> filter;


    public Mana(Color color, Card source, Function<Object, Boolean> filter = (o) -> true) {
        this.color = color;
        this.source = source;
        this.filter = filter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mana m = (Mana) o;
        return color == m.color;
    }

    @Override
    public String toString() {
        return color.toString();
    }
}
