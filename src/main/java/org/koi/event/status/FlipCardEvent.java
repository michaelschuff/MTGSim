package org.koi.event.status;

import org.koi.game.MTGGame;
import org.koi.event.Event;
import org.koi.gameobject.card.Card;
import org.koi.zone.ZONE;

public class FlipCardEvent extends Event {
    public Card c;
    public FlipCardEvent(MTGGame game,
                         Card source,
                         Card c) {
        super(game, source);
        this.c = c;
    }

    @Override
    public boolean resolve() {
        if (game.data.getZoneType(c) != ZONE.BATTLEFIELD)
            return false;


        c.status.flipped = !c.status.flipped;

        return true;
    }
}
