package org.koi.turn;

import org.koi.util.Variant;

public class TurnController {
    private int turnCount;
    private PlayerCounter playerCounter;
    private final int playerCount;
    private int playerPriorityIndex;
    private Phase phase;

    public TurnController(int playerCount) {
        this.playerCount = playerCount;
        reset(playerCount);
    }


    // Resets priority to AP
    public void ResetPriority() {
        playerPriorityIndex = playerCounter.getWhoseTurn();
    }

    public boolean ActivePlayerHasPriority() {
        return playerPriorityIndex == playerCounter.getWhoseTurn();
    }

    public PhaseType getPhase() {
        return phase.getType();
    }

    public int getPriorityIndex() {
        return playerPriorityIndex;
    }

    public int getWhoseTurn() {
        return playerCounter.getWhoseTurn();
    }

    // Returns true if it rolled over
    public int IncTurn() {
        turnCount++;
        return playerCounter.Next();
    }

    // Returns true if wrapped
    public boolean IncPhase() {
        return phase.Next();
    }

    // Returns true if it rolled over
    public boolean IncPriority() {
        playerPriorityIndex++;
        if (playerPriorityIndex >= playerCount) {
            playerPriorityIndex = 0;
            return true;
        }
        return false;
    }
    public int numPlayersAlive() {
        return this.playerCounter.numPlayersAlive();
    }

    public boolean TwoOrMorePlayers() {
        return this.playerCounter.TwoOrMorePlayers();
    }

    public boolean isPriorityPhase() {
        return phase.GivesPriority();
    }

    public void reset(int playerCount) {
        this.turnCount = 0;
        this.playerCounter = new PlayerCounter(playerCount);
        this.playerPriorityIndex = 0;
        this.phase = new Phase();
    }

    public String toString() {
        String ret = "";
        ret += "Turn " + turnCount;
        ret += "\nPhase " + getPhase();
        ret += "\n\tPlayer " + playerCounter.getWhoseTurn() + "'s turn";
        ret += "\n\tPlayer " + playerPriorityIndex + " has priority";
        return ret;
    }
}
