package org.koi.event.zonechange.action;

import org.koi.event.Event;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.Player;

public abstract class SpecialActionEvent extends Event {
    public Player controller;
    public SpecialActionEvent(MTGGame game,
                              Card source,
                              Player controller) {
        super(game, source);
        this.controller = controller;
    }
}
