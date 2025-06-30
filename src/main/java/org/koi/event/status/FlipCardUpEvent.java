package org.koi.event.status;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.game.zone.ZONE;

public class FlipCardUpEvent extends FlipCardEvent {
    public FlipCardUpEvent(MTGGame game,
                           Card source,
                           Card c) {
        super(game, source, c);
    }

    @Override
    public boolean resolve() {
        if (!c.status.flipped)
            return false;


        if (game.data.getZoneType(c) != ZONE.BATTLEFIELD)
            return false;


        c.status.flipped = false;

        return true;
    }
}
