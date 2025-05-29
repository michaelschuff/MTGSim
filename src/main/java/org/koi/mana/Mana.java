package org.koi.mana;

import org.koi.util.OID;
import org.koi.util.Color;

import java.util.function.Function;

public class Mana {
    public Color color;
    public OID source;
    public Function<Object, Boolean> filter;


    public Mana(Color color, OID source, Function<Object, Boolean> filter = (o) -> true) {
        this.color = color;
        this.source = source;
        this.filter = filter;
    }
}
