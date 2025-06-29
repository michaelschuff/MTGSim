package org.koi.gameobject;

public class TimestampGenerator {
    int timestamp = 0;
    public TimestampGenerator() { }

    public int getNew() {
        return timestamp++;
    }
}
