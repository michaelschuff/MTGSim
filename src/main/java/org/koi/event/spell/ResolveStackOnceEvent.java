package org.koi.event.spell;

import org.koi.event.Event;
import org.koi.event.zonechange.ObjectMoveZoneEvent;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.gameobject.GameObject;
import org.koi.gameobject.ability.ActivatedAbility;
import org.koi.gameobject.ability.SpellAbility;
import org.koi.gameobject.ability.StackAbility;

public class ResolveStackOnceEvent extends Event {
    public ResolveStackOnceEvent(MTGGame game) {
        super(game);
    }

    @Override
    public boolean resolve() {
        GameObject o = game.data.theStack.peek();
        if (o instanceof Card) {
            Card c = (Card) o;
            if (c.typeline.isPermanent()) {
                game.eventManager.addEvent(
                        new ObjectMoveZoneEvent(
                                game, null, c,
                                game.data.theStack, game.data.battlefield
                        )
                );
            } else {
                for (SpellAbility sa : c.abilities.spellAbilities) {
                    if (sa.condition.apply(game)) {
                        game.eventManager.addEvents(sa.effects);
                    }
                }
            }
        } else if (o instanceof StackAbility) {
            StackAbility sa = (StackAbility) o;
            if (sa.ability_source instanceof SpellAbility) {
                SpellAbility spell_ability = (SpellAbility) sa.ability_source;
                if (spell_ability.condition.apply(game)) {
                    game.eventManager.addEvents(spell_ability.effects);
                }
            } else if (sa.ability_source instanceof ActivatedAbility) {
                ActivatedAbility aa = (ActivatedAbility) sa.ability_source;
                game.eventManager.addEvents(aa.effects);
            }
        }
        return false;
    }
}
