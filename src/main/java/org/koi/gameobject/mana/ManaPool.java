package org.koi.gameobject.mana;

import org.koi.gameobject.cost.ManaCost;
import org.koi.util.Color;

import java.util.ArrayList;

public class ManaPool extends ArrayList<Mana> {
    public ManaPool() { }
    public ManaPool(ManaPool o) {
        this.addAll(o);
    }

    public void emptyOut() {
        //TODO: dont clear out mana that isnt supposed to.
        this.clear();
    }

    // returns the mana pool after the cost is paid
    // returns null if not possible
    public ManaPool canAfford(ManaCost cost) {
        // TODO: make this better -- handle hybrid types and such

        ManaPool mp = new ManaPool(this);

        for (ManaSymbol s : cost) {
            if (s.generic) {
                continue;
            }

            if (s.white) {
                boolean success = mp.remove(new Mana(Color.WHITE, null));
                if (!success) return null;
            }
            if (s.blue) {
                boolean success = mp.remove(new Mana(Color.BLUE, null));
                if (!success) return null;
            }
            if (s.black) {
                boolean success = mp.remove(new Mana(Color.BLACK, null));
                if (!success) return null;
            }
            if (s.red) {
                boolean success = mp.remove(new Mana(Color.RED, null));
                if (!success) return null;
            }
            if (s.green) {
                boolean success = mp.remove(new Mana(Color.GREEN, null));
                if (!success) return null;
            }
        }
        return mp;

    }

}
