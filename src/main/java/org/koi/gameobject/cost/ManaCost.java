package org.koi.gameobject.cost;

import org.koi.gameobject.mana.ManaSymbol;
import org.koi.util.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ManaCost implements Iterable<ManaSymbol>, Payable {
    private final ArrayList<ManaSymbol> manaSymbols;

    ManaCost(List<ManaSymbol> symbols) {
        this.manaSymbols = new ArrayList<>(symbols);
    }

    public ManaCost unaryMinus() {
        ArrayList<ManaSymbol> ret = new ArrayList<>();
        for (ManaSymbol s : manaSymbols) {
            ret.add(-s);
        }
        return new ManaCost(ret);
    }

    public int cmc() {
        int sum = 0;
        for (ManaSymbol m : manaSymbols) {
            sum += m.cmc();
        }
        return sum;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (ManaSymbol m : this.manaSymbols) {
            s.append(m.toString());
        }
        return s.toString();
    }

    public ManaSymbol get(int index) {
        return manaSymbols.get(index);
    }

    @Override
    public Iterator<ManaSymbol> iterator() {
        return new ManaCostIterator(this.manaSymbols);
    }

    @Override
    public boolean canPay(Player player) {
        return player.data.manaPool.canAfford(this) != null;
    }

    @Override
    public void pay(Player player) {
        player.data.manaPool = player.data.manaPool.canAfford(this);
    }
}
