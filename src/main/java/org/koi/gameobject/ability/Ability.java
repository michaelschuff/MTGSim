package org.koi.gameobject.ability;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;

public abstract class Ability {
    public MTGGame game;
    public Card source;
    public Ability(MTGGame game, Card source) {
        this.game = game;
        this.source = source;
    }

    @Override
    public String toString() {
        return this.getClass().toString();
    }
}
