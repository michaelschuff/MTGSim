package org.koi.event.statebasedactions;

import org.koi.event.Event;
import org.koi.event.EventListener;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.Player;

public abstract class IllegalTokenLocationsSBA {
    public static EventListener get() {
        return new EventListener() {
            @Override
            public void accept(MTGGame game, Event event) {
                game.data.theStack.removeIf((o) -> {
                    if (o instanceof Card) {
                        return ((Card) o).isToken;
                    }
                    return false;
                });
                game.data.commandZone.removeIf(c -> c.isToken);
                game.data.exile.removeIf(c -> c.isToken);

                for (Player p : game.data.players) {
                    p.data.hand.removeIf(c -> c.isToken);
                    p.data.graveyard.removeIf(c -> c.isToken);
                    p.data.library.removeIf(c -> c.isToken);
                }
            }
        };
    }
}
