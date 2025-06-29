package org.koi.event.status;

import org.koi.game.MTGGame;
import org.koi.event.Event;
import org.koi.gameobject.card.Card;
import org.koi.zone.ZONE;

public class CardFaceUpDownEvent extends Event {
    public Card c;
    public CardFaceUpDownEvent(MTGGame game,
                               Card source,
                               Card c) {
        super(game, source);
        this.c = c;
    }

    @Override
    public boolean resolve() {
        if (game.data.getZoneType(c) != ZONE.BATTLEFIELD)
            return false;


        c.status.faceUp = !c.status.faceUp;

        return true;
    }
}
