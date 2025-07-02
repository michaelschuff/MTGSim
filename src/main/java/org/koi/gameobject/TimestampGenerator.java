package org.koi.gameobject;

public class TimestampGenerator {
    int timestamp = 0;

    public int getNew() {
        return timestamp++;
    }
}
