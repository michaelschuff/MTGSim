package org.koi.event;

import org.koi.util.OID;

import java.util.function.Function;

public abstract class Event {
    public final boolean isRevertible;
    public final boolean doesTarget;
    public final Function<OID, Boolean> filter;


    public abstract void ApplyAction();

    public Event(boolean isRevertible,
                 Function<OID, Boolean> filter,
                 boolean doesTarget) {
        this.isRevertible = isRevertible;
        this.doesTarget = doesTarget;
        this.filter = filter;
    }

}
