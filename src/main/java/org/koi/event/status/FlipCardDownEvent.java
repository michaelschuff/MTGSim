package org.koi.event.status;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.zone.ZONE;

public class FlipCardDownEvent extends FlipCardEvent {
    public FlipCardDownEvent(MTGGame game,
                             Card source,
                             Card c) {
        super(game, source, c);
    }

    @Override
    public boolean resolve() {
        if (c.status.flipped)
            return false;
        if (game.data.getZoneType(c) != ZONE.BATTLEFIELD)
            return false;


        c.status.flipped = true;

        return true;
    }
}
