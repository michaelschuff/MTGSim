package org.koi.game.turn;

import java.util.ArrayList;
import java.util.List;

public class PlayerCounter {
    private final List<Boolean> playerStatus;
    private int turnCounter;
    private final int numPlayers;



    public PlayerCounter(int numPlayers) {
        this.playerStatus = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            this.playerStatus.add(true);
        }
        this.turnCounter = 0;
        this.numPlayers = numPlayers;
    }

    public void PlayerLost(int playerIndex) {
        playerStatus.set(playerIndex, false);
    }

    public int Next() {
        do {
            turnCounter = (turnCounter + 1) % numPlayers;
        } while (!playerStatus.get(turnCounter)); // skip players that have lost

        return turnCounter;
    }
    public int getWhoseTurn() {
        return turnCounter;
    }

    public int numPlayersAlive() {
        int counter = 0;
        for (Boolean b : playerStatus) {
            if (b) {
                counter++;
            }
        }
        return counter;
    }

    public boolean TwoOrMorePlayers() {
        return numPlayersAlive() > 1;
    }
}
