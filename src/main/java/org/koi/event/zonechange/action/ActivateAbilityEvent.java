package org.koi.event.zonechange.action;

import org.koi.event.mana.AddManaEvent;
import org.koi.game.MTGGame;
import org.koi.event.Event;
import org.koi.gameobject.card.Card;
import org.koi.gameobject.ability.ActivatedAbility;
import org.koi.gameobject.ability.StackAbility;
import org.koi.util.Player;

public class ActivateAbilityEvent extends Event {
    public Card source;
    public Player controller;
    public ActivatedAbility ability;

    public ActivateAbilityEvent(MTGGame game,
                                Card source,
                                Player controller,
                                ActivatedAbility ability) {
        super(game, source);
        this.source = source;
        this.controller = controller;
        this.ability = ability;
    }

    @Override
    public boolean resolve() {
        boolean manaAbility = false;
        for (Event e : ability.effects) {
            if (e instanceof AddManaEvent) {
                manaAbility = true;
            }
        }
        if (manaAbility) {
            game.eventManager.addEvents(ability.effects);
            game.eventManager.resolveEvents();
            return true;
        } else {
            StackAbility sa = new StackAbility(game, source, controller, controller, ability);
            game.data.abilityMap.put(sa.id, sa);
            game.data.theStack.push(sa);
            return true;
        }
    }
}
