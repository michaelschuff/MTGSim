package org.koi;

import org.koi.gameobject.Card;
import org.koi.gameobject.MTGCard;
import org.koi.util.OID;
import org.koi.turn.TurnController;
import org.koi.util.Variant;
import org.koi.zone.ZONE;

import java.util.List;
import java.util.Scanner;

public class MTGGame {
    private final TurnController turnController;
    public final GameData gameData;


    private final Scanner scanner = new Scanner(System.in);

    private MTGGame(Variant variant, List<List<MTGCard>> decklists) {
        switch(variant) {
            case Standard:
                this.turnController = new TurnController(2);
                this.gameData = new GameData(decklists);
                break;
            case Commander: default:
                this.turnController = new TurnController(4);
                this.gameData = new GameData(decklists);
                break;
        }
    }

    public void start() {
        // Main loop
        System.out.println("Player " + turnController.getWhoseTurn() + "'s turn");
        while (turnController.TwoOrMorePlayers()) {
            // Beginning of step
            this.gameData.updateBoardstate();
            this.gameData.startOfStep(turnController.getPhase());
            this.gameData.updateBoardstate();
            if (turnController.isPriorityPhase() || !gameData.isStackEmpty()) {
                // Loop until priority has finished
                while (true) {
                    // Active player gets priority
                    turnController.ResetPriority();
                    int passCount = 0;
                    // Loop until all players have passed in a row
                    while (true) {
                        // Loop until current player has passed
                        while (true) {
                            this.gameData.updateBoardstate();
                            this.gameData.stateBasedActions();
                            this.gameData.updateBoardstate();

                            // TODO: flesh out player actions
                            String passed = scanner.nextLine();
                            if ("pass".equals(passed)) {
                                break;
                            } else {
                                passCount = 0; // someone didn't pass, so reset priority
                            }
                        }
                        passCount++;
                        System.out.println("Player " + turnController.getPriorityIndex() + " passed. ");

                        // Switch priority if all players have passed
                        if (passCount < turnController.numPlayersAlive()) {
                            turnController.IncPriority();
                            System.out.println("Player " + turnController.getPriorityIndex() + " has priority.");
                        } else {
                            System.out.println();
                            break;
                        }
                    }
                    if (!gameData.isStackEmpty()) {
                        this.gameData.updateBoardstate();
                        this.gameData.resolveTopOfStack();
                    } else {
                        break;
                    }
                }
            }

            // ============================================
            // End of step
            // ============================================
            // update gamestate
            this.gameData.updateBoardstate();
            this.gameData.endOfTurn();

            boolean endturn = turnController.IncPhase();
            if (endturn) {
                this.turnController.IncTurn();
                System.out.println("\n\n\nPlayer " + turnController.getWhoseTurn() + "'s turn");
            }
            System.out.println("Next Phase: " + turnController.getPhase());
        }
    }




    // Thread Local stuff
    private static final ThreadLocal<MTGGame> _localStorage = new ThreadLocal<MTGGame>(){
        protected MTGGame initialValue() {
            return new MTGGame(Variant.Commander, List.of(List.of(),List.of(),List.of(),List.of()));
        }
    };

    public static MTGGame getInstance(){
        return _localStorage.get();
    }

    public static GameData data(){
        return _localStorage.get().gameData;
    }

    public static void setInstance(Variant variant, List<List<MTGCard>> decklists){
        _localStorage.set(new MTGGame(variant, decklists));
    }
}
