package org.koi.zone;

import org.koi.util.OID;

import java.util.*;

public abstract class AbstractZone extends Stack<OID> {
    public void shuffle() {
        Collections.shuffle(this);
    }
    public abstract ZONE getType();
}
