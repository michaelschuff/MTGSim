package org.koi.gameobject;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public abstract class GameObject {
    public int timestamp;
    public UUID id;
    public GameObject(int timestamp) {
        this.timestamp = timestamp;
        this.id = randomUUID();
    }
}
