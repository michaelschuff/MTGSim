package org.koi.gameobject.ability;

import org.koi.game.MTGGame;
import org.koi.util.OID;
import org.koi.util.Player;

public abstract class Ability {
    public Ability(MTGGame game) { }

    @Override
    public String toString() {
        return Ability.class.toString();
    }
}
