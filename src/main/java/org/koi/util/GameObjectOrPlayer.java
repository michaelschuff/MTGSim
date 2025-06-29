package org.koi.util;

import org.koi.gameobject.GameObject;

public class GameObjectOrPlayer {
    public GameObject o;
    public Player player;

    public GameObjectOrPlayer(GameObject o) {
        this.o = o;
        this.player = null;
    }
    public GameObjectOrPlayer(Player p) {
        this.o = null;
        this.player = p;
    }

    public boolean isGameObject() {
        return o != null;
    }
    public boolean isPlayer() {
        return player != null;
    }

    @Override
    public String toString() {
        if (isPlayer()) {
            return player.toString();
        } else if (isGameObject()) {
            return o.toString();
        } else {
            return "Not a player or GameObject";
        }
    }
}
