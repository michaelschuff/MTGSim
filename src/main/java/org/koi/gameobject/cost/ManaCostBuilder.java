package org.koi.gameobject.cost;

import org.koi.gameobject.mana.ManaSymbol;
import org.koi.gameobject.mana.ManaSymbolBuilder;
import org.koi.util.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManaCostBuilder {
    private final ArrayList<ManaSymbol> manaSymbols = new ArrayList<>();

    public ManaCostBuilder() {

    }
    public ManaCostBuilder(ManaCost mc) {
        for (ManaSymbol m : mc)
            manaSymbols.add(m);
    }

    public ManaCostBuilder add(ManaSymbol symbol) {
        manaSymbols.add(symbol);
        return this;
    }
    public ManaCostBuilder add(ManaCost mc) {
        for (ManaSymbol s : mc) {
            this.add(s);
        }
        return this;
    }


    // TODO: cancel s and -s if both exist
    public ManaCost build() {
        Collections.sort(manaSymbols);

        // merge non-variable generic symbols
        ManaSymbol m = ManaSymbol.ZERO;

        // TODO: I think index is always 0
        // need index outside of loop
        int i = 0;
        while (i < manaSymbols.size()) {
            ManaSymbol temp = manaSymbols.get(i);

            if (temp.isColor(Color.GENERIC) && temp.cmc() >= 0) {
                m = new ManaSymbolBuilder()
                        .generic(m.cmc() + temp.cmc())
                        .build();
            } else {
                break;
            }
            i++;
        }
        List<ManaSymbol> trimmedSymbols = manaSymbols.subList(i, manaSymbols.size());
//        if (m.cmc() != 0 || trimmedSymbols.isEmpty()) {
        trimmedSymbols.add(m);
        Collections.sort(trimmedSymbols);
//        }
        return new ManaCost(trimmedSymbols);
    }
}
