package org.koi.event.status;

import org.koi.game.MTGGame;
import org.koi.event.Event;
import org.koi.gameobject.card.Card;
import org.koi.game.zone.ZONE;

public class PhaseInCardEvent extends Event {
    public Card c;
    public PhaseInCardEvent(MTGGame game,
                            Card source,
                            Card c) {
        super(game, source);
        this.c = c;
    }

    @Override
    public boolean resolve() {
        if (c.status.phasedIn)
            return false;
        if (game.data.getZoneType(c) != ZONE.BATTLEFIELD)
            return false;


        c.status.phasedIn = true;

        return true;
    }
}