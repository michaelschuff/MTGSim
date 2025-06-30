package org.koi.event.status;

import org.koi.game.MTGGame;
import org.koi.event.Event;
import org.koi.gameobject.card.Card;
import org.koi.game.zone.ZONE;

public class TapCardEvent extends Event {
    public Card c;
    public TapCardEvent(MTGGame game,
                        Card source,
                        Card c) {
        super(game, source);
        this.c = c;
    }

    @Override
    public boolean resolve() {
        if (c.status.tapped)
            return false;
        if (game.data.getZoneType(c) != ZONE.BATTLEFIELD)
            return false;

        c.status.tapped = true;

        return true;
    }
}
