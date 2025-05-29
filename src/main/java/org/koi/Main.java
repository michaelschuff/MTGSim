package org.koi;


import org.koi.gameobject.MTGCard;
import org.koi.gameobject.cards.LegionsInitiative;
import org.koi.gameobject.cards.OnakkeOgre;
import org.koi.gameobject.cards.WishcoinCrab;
import org.koi.util.Variant;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // TODO: get decks from players
        List<MTGCard> deck1 = new ArrayList<>();
        deck1.add(OnakkeOgre.getCard());
        deck1.add(WishcoinCrab.getCard());
        deck1.add(LegionsInitiative.getCard());

        List<MTGCard> deck2 = new ArrayList<>();
        deck2.add(OnakkeOgre.getCard());
        deck2.add(WishcoinCrab.getCard());
        deck2.add(LegionsInitiative.getCard());


        Thread thread = new Thread(new KoiSimulator(Variant.Standard, List.of(deck1, deck2)));
        thread.start();
    }
}