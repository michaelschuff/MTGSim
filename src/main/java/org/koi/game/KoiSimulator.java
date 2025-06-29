package org.koi.game;


import org.koi.util.Variant;

import java.util.List;

public final class KoiSimulator implements Runnable {
    public final int numPlayers;
    public final int numRounds;
    public final Variant variant;
    public final List<List<String>> decklists;




    public KoiSimulator(Variant variant, List<List<String>> decks) {
        this.decklists = decks;
        this.variant = variant;
        switch(variant) {
            case Standard:
                this.numPlayers = 2;
                this.numRounds = 3;
                break;
            case Commander:
            default:
                this.numPlayers = 4;
                this.numRounds = 1;
                break;
        }
    }

    @Override
    public void run() {
        // TODO: get decklists from ppl somehow


        for (int i = 0; i < this.numRounds; i++) {
            MTGGame game = new MTGGame(this.variant);
            game.start(this.decklists);
            // TODO: handle result somehow
        }
    }

}
