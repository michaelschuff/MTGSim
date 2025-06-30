package org.koi.event.statebasedactions;

import org.koi.event.Event;
import org.koi.event.EventListener;
import org.koi.event.zonechange.ObjectMoveZoneEvent;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;

public abstract class CreatureReceivedDeathtouchDamageSBA {
    public static EventListener get() {
        return new EventListener() {
            @Override
            public void accept(MTGGame game, Event event) {
                for (Card c : game.data.battlefield) {
                    if (c.typeline.isType("Creature") && c.pt.toughness() > 0 && c.status.recievedDeathtouchDamageFlag) {
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
                        c.status.recievedDeathtouchDamageFlag = false;

                    }

                }
            }
        };
    }
}
