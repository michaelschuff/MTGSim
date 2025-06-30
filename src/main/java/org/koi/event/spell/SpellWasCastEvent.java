package org.koi.event.spell;

import org.koi.event.Event;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;

public class SpellWasCastEvent extends Event {
    public Card spell;
    public SpellWasCastEvent(MTGGame game, Card spell) {
        super(game);
        this.spell = spell;
    }

    @Override
    public boolean resolve() {
        return true;
    }
}
