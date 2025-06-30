package org.koi.event.statebasedactions;

import org.koi.event.Event;
import org.koi.event.EventListener;
import org.koi.event.result.GameLossReason;
import org.koi.event.result.PlayerLosesGameEvent;
import org.koi.game.MTGGame;
import org.koi.gameobject.counters.Counter;
import org.koi.util.Player;


public abstract class PoisonCountersSBA {
    public static EventListener get() {
        return new EventListener() {
            @Override
            public void accept(MTGGame game, Event event) {
                for (Player p : game.data.players) {
                    if (p.data.counters.getAmount(Counter.POISON) >= 10) {
                        game.eventManager.addEvent(
                                new PlayerLosesGameEvent(
                                        game, p,
                                        GameLossReason.POISON_COUNTERS
                                )
                        );
                    }
                }
            }
        };
    }
}