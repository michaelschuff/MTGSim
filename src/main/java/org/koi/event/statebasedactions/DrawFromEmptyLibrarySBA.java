package org.koi.event.statebasedactions;

import org.koi.event.Event;
import org.koi.event.EventListener;
import org.koi.event.result.GameLossReason;
import org.koi.event.result.PlayerLosesGameEvent;
import org.koi.game.MTGGame;
import org.koi.util.Player;

public abstract class DrawFromEmptyLibrarySBA {
    public static EventListener get() {
        return new EventListener() {
            @Override
            public void accept(MTGGame game, Event event) {
                for (Player p : game.data.players) {
                    if (p.data.drewFromEmptyLibraryFlag) {
                        game.eventManager.addEvent(
                                new PlayerLosesGameEvent(
                                        game, p,
                                        GameLossReason.DRAW_FROM_EMPTY_LIBRARY
                                )
                        );
                        p.data.drewFromEmptyLibraryFlag = false;
                    }

                }
            }
        };
    }
}
