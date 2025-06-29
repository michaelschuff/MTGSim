package org.koi.zone;


import java.util.*;

public abstract class AbstractZone<T> extends Stack<T> {
    public void shuffle() {
        Collections.shuffle(this);
    }
    public abstract ZONE getType();

}
