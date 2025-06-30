package org.koi.gameobject.ability;

import org.koi.event.Event;
import org.koi.event.EventListener;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;

public class TriggeredAbility extends Ability {
    public Class<? extends Event> triggerType;
    public EventListener listener;
    public <T extends Event> TriggeredAbility(MTGGame game,
                                              Card source,
                                              Class<T> triggerType,
                                              EventListener listener) {
        super(game, source);
        this.triggerType = triggerType;
        this.listener = listener;
    }
}
