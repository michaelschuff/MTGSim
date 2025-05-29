package org.koi.gameobject.ability;

import org.koi.LAYER;
import org.koi.MTGGame;
import org.koi.gameobject.Card;
import org.koi.modification.Modification;
import org.koi.util.OID;

import java.util.List;
import java.util.function.Function;

public class ContinuousEffect implements Comparable<ContinuousEffect> {
    public int controller;
    public Modification effect;
    public Card source;
    public Function<Integer, List<OID>> filter;
    public List<OID> affectedObjects = null;

    public ContinuousEffect(int controller,
                            Modification e,
                            Card s,
                            Function<Integer, List<OID>> filter) {
        this.controller = controller;
        this.effect = e;
        this.source = s;
        this.filter = filter;
    }
    public void selectAffectedObjects() {
        if (affectedObjects == null) {
            this.affectedObjects = this.filter.apply(controller);
        }
    }

    public void applyToObjects(LAYER l) {
        assert affectedObjects != null;
        for (OID id : affectedObjects) {
            Card c = MTGGame.data().getCard(id);
            c = effect.apply(c, l);
            MTGGame.data().setCard(id, c);
        }
    }


    @Override
    public int compareTo(ContinuousEffect o) {
        return 0;
    }
}
