package org.koi.gameobject.ability;

import org.koi.event.Event;
import org.koi.event.EventListener;
import org.koi.game.MTGGame;

public class TriggeredAbility extends Ability {
    public Class<? extends Event> triggerType;
    public EventListener listener;
    public <T extends Event> TriggeredAbility(MTGGame game,
                                              Class<T> triggerType,
                                              EventListener listener) {
        super(game);
        this.triggerType = triggerType;
        this.listener = listener;
    }
}
