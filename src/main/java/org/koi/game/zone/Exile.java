package org.koi.game.zone;

import org.koi.gameobject.card.Card;

public class Exile extends AbstractZone<Card> {

    @Override
    public ZONE getType() {
        return ZONE.EXILE;
    }
}
