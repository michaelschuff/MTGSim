package org.koi.game.zone;

import org.koi.gameobject.card.Card;

public class Hand extends AbstractZone<Card> {

    @Override
    public ZONE getType() {
        return ZONE.HAND;
    }
}
