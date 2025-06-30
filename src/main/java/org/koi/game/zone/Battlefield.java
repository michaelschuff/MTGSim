package org.koi.game.zone;


import org.koi.gameobject.card.Card;

public class Battlefield extends AbstractZone<Card> {
    public void resetCards() {
        this.forEach((id) -> {
            // TODO: get card by id
//            card.reset();
        });
    }

    @Override
    public ZONE getType() {
        return ZONE.BATTLEFIELD;
    }
}
