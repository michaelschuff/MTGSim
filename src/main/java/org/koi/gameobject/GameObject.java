package org.koi.gameobject;

import org.koi.util.GameObjectOrPlayer;

import java.util.UUID;

public abstract class GameObject {
    public int timestamp;
    public UUID id = UUID.randomUUID();


    public GameObject(int timestamp) {
        this.timestamp = timestamp;
    }



    public GameObjectOrPlayer asGameObjectOrPlayer() {
        return new GameObjectOrPlayer(this);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GameObject other = (GameObject) obj;
        return id.equals(other.id);
    }
}
