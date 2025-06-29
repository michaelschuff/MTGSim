package org.koi.gameobject.ability;

import org.koi.LAYER;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.modification.Modification;
import org.koi.util.Player;

import java.util.List;
import java.util.function.Function;

public class ContinuousEffect {
    public Player controller;
    public Modification effect;
    public Card source;
    public Function<Card, List<Card>> filter;
    public List<Card> affectedObjects = null;
    public final MTGGame game;

    public ContinuousEffect(MTGGame game,
                            Card source,
                            Modification e,
                            Function<Card, List<Card>> filter) {
        this.game = game;
        this.controller = source.controller;
        this.effect = e;
        this.source = source;
        this.filter = filter;
    }
    public void selectAffectedObjects() {
        if (affectedObjects == null) {
            this.affectedObjects = this.filter.apply(source);
        }
    }

    public void applyToObjects(LAYER l) {
        assert affectedObjects != null;
        for (Card c : affectedObjects) {
            effect.apply(c, l);
        }
    }
}
