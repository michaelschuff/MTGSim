package org.koi.event.zonechange.action;

import org.koi.event.Event;
import org.koi.game.MTGGame;
import org.koi.util.Player;

public class PassPriorityEvent extends Event {
    public PassPriorityEvent(MTGGame game,
                             Player source) {
        super(game, source);
    }

    @Override
    public boolean resolve() {
        return true;
    }

}
