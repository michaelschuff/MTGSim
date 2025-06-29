package org.koi.util;

import java.util.UUID;

public class Player implements Comparable<Player> {
    public final UUID id = UUID.randomUUID();
    public final int index;
    public PlayerData data;

    public Player(int index) {
        this.index = index;
        this.data = new PlayerData();
    }


    public GameObjectOrPlayer asGameObjectOrPlayer() {
        return new GameObjectOrPlayer(this);
    }

    @Override
    public int compareTo(Player o) {
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
        Player other = (Player) obj;
        return id.equals(other.id);
    }
}
