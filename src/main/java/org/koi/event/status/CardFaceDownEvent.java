package org.koi.event.status;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.zone.ZONE;

public class CardFaceDownEvent extends CardFaceUpDownEvent {
    public CardFaceDownEvent(MTGGame game,
                             Card source,
                             Card c) {
        super(game, source, c);
    }

    @Override
    public boolean resolve() {
        if (!c.status.faceUp)
            return false;
        if (game.data.getZoneType(c) != ZONE.BATTLEFIELD)
            return false;


        c.status.faceUp = false;

        return true;
    }
}
