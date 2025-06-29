package org.koi.event.zonechange.action;

import org.koi.game.MTGGame;
import org.koi.event.Event;
import org.koi.gameobject.card.Card;
import org.koi.gameobject.ability.StackAbility;
import org.koi.gameobject.ability.TriggeredAbility;
import org.koi.util.Player;

public class TriggerAbilityEvent extends Event {
    public Card source;
    public Player controller;
    public TriggeredAbility ability;

    public TriggerAbilityEvent(MTGGame game,
                               Card source,
                               TriggeredAbility ability) {
        super(game, source);
        this.source = source;
        this.controller = source.controller;
        this.ability = ability;
    }

    @Override
    public boolean resolve() {
        StackAbility sa = new StackAbility(game, source, controller, controller, ability);
        game.data.abilityMap.put(sa.id, sa);
        game.data.theStack.push(sa);
        return true;
    }
}
