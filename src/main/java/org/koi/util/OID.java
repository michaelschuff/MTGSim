package org.koi.util;

import java.util.UUID;

// Object ID
public class OID implements Comparable<OID> {
    public final UUID id = UUID.randomUUID();

    public OID() { }

    @Override
    public int compareTo(OID o) {
        if (o == null) return -1;
        return id.compareTo(o.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OID other = (OID) obj;
        return id.equals(other.id);
    }
}
