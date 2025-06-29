package org.koi;


import org.koi.game.KoiSimulator;
import org.koi.util.Variant;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // TODO: get decks from players
        List<String> deck1 = new ArrayList<>();
        deck1.add("OnakkeOgre");
        deck1.add("WishcoinCrab");
        deck1.add("LegionsInitiative");
        deck1.add("Mountain");
        deck1.add("Plains");
        deck1.add("Swamp");
        deck1.add("Forest");
        deck1.add("Island");
        deck1.add("Mountain");
        deck1.add("Plains");


        List<String> deck2 = new ArrayList<>();
        deck2.add("OnakkeOgre");
        deck2.add("WishcoinCrab");
        deck2.add("LegionsInitiative");
        deck2.add("Mountain");
        deck2.add("Plains");
        deck2.add("Swamp");
        deck2.add("Forest");
        deck2.add("Island");
        deck2.add("Mountain");
        deck2.add("Plains");


        KoiSimulator sim = new KoiSimulator(Variant.Standard, List.of(deck1, deck2));
        sim.run();
    }
}