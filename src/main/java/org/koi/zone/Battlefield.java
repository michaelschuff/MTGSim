package org.koi.zone;


public class Battlefield extends AbstractZone {
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
