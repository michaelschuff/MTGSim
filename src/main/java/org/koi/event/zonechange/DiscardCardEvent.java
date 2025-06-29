package org.koi.event.zonechange;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;

public class DiscardCardEvent extends ObjectMoveZoneEvent {
    public Card c;

    public DiscardCardEvent(MTGGame game,
                            Card source,
                            Card c) {
        super(game,
                source.asGameObjectOrPlayer(), c,
                c.owner.data.hand,
                c.owner.data.graveyard);
    }
    public DiscardCardEvent(MTGGame game,
                            Card c) {
        super(game,
                null, c,
                c.owner.data.hand,
                c.owner.data.graveyard);
    }


    @Override
    public boolean resolve() {
        if (c.owner.data.hand.contains(c)) {
            c.owner.data.hand.remove(c);
            c.owner.data.graveyard.push(c);
            return true;
        }
        return false;
    }
}
