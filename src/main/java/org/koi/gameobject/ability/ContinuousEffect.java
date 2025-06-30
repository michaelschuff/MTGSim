package org.koi.gameobject.ability;

import org.koi.LAYER;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.modification.Modification;
import org.koi.util.Player;

import java.util.List;

public abstract class ContinuousEffect {
    public Player controller;
    public Modification effect;
    public Card source;
    public List<Card> affectedObjects = null;
    public final MTGGame game;

    public ContinuousEffect(MTGGame game,
                            Card source,
                            Modification e) {
        this.game = game;
        this.controller = source.controller;
        this.effect = e;
        this.source = source;
    }
    public void setAffectedObjects() {
        if (affectedObjects == null) {
            affectedObjects = this.getSubjects();
        }
//        return affectedObjects;
    }

    public void applyToObjects(LAYER l) {
        assert affectedObjects != null;
        for (Card c : affectedObjects) {
            effect.apply(c, l);
        }
    }
    public abstract List<Card> getSubjects();
}
