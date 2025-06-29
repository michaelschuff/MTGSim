package org.koi.event.zonechange.action;

import org.koi.game.MTGGame;
import org.koi.event.zonechange.ObjectMoveZoneEvent;
import org.koi.gameobject.card.Card;
import org.koi.util.Player;

public abstract class PlayCardEvent extends ObjectMoveZoneEvent {
    public PlayCardEvent(MTGGame game,
                         Player source,
                         Card c) {
        super(game, source.asGameObjectOrPlayer(), c, game.data.getZone(c), game.data.theStack);
    }
}
