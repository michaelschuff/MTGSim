package org.koi.event.zonechange;

import org.koi.game.MTGGame;
import org.koi.event.Event;
import org.koi.gameobject.card.Card;
import org.koi.util.GameObjectOrPlayer;
import org.koi.zone.AbstractZone;
import org.koi.zone.ZONE;

public class ObjectMoveZoneEvent extends Event {
    public Card c;
    public AbstractZone from_zone;
    public AbstractZone to_zone;

    public ObjectMoveZoneEvent(MTGGame game,
                               GameObjectOrPlayer source,
                               Card c,
                               AbstractZone from,
                               AbstractZone to) {
        super(game, source);
        this.c = c;
        this.from_zone = from;
        this.to_zone = to;
    }

    @Override
    public boolean resolve() {
        ZONE current_zone = game.data.getZoneType(c);
        if (current_zone == from_zone.getType()) {
            if (from_zone.contains(c)) {
                from_zone.remove(c);
                to_zone.add(c);
                return true;
            }
        }
        return false;
    }
}
