package org.koi.event.statebasedactions;

import org.koi.event.Event;
import org.koi.event.EventListener;
import org.koi.game.MTGGame;
import org.koi.util.Player;

public abstract class IllegalCopyLocationSBA {
    public static EventListener get() {
        return new EventListener() {
            @Override
            public void accept(MTGGame game, Event event) {
                // TODO 704.5e. If a copy of a spell is in a zone other than the stack, it ceases to exist. ...
                //              IDK if we need to do this, nor how we differentiate permanent copies

                game.data.commandZone.removeIf(c -> c.isSpellCopy);
                game.data.exile.removeIf(c -> c.isSpellCopy);
                for (Player p : game.data.players) {
                    p.data.hand.removeIf(c -> c.isSpellCopy);
                    p.data.graveyard.removeIf(c -> c.isSpellCopy);
                    p.data.library.removeIf(c -> c.isSpellCopy);
                }
            }
        };
    }
}
