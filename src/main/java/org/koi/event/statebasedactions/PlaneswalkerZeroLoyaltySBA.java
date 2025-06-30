package org.koi.event.statebasedactions;

import org.koi.event.Event;
import org.koi.event.EventListener;
import org.koi.event.zonechange.ObjectMoveZoneEvent;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.gameobject.counters.Counter;

public abstract class PlaneswalkerZeroLoyaltySBA {
    public static EventListener get() {
        return new EventListener() {
            @Override
            public void accept(MTGGame game, Event event) {
                for (Card c : game.data.battlefield) {
                    if (c.typeline.isType("Planeswalker") && c.counters.getAmount(Counter.LOYALTY) == 0) {
                        //TODO: replace this with destroyed event
                        game.eventManager.addEvent(
                                new ObjectMoveZoneEvent(
                                        game,
                                        null,
                                        c,
                                        game.data.battlefield,
                                        c.owner.data.graveyard
                                )
                        );
                    }

                }
            }
        };
    }
}
