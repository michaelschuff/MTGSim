package org.koi.game.zone;

import org.koi.gameobject.card.Card;

public class Graveyard extends AbstractZone<Card> {

    @Override
    public ZONE getType() {
        return ZONE.GRAVEYARD;
    }
}
