package org.koi.event.zonechange.action;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.Player;

public class CastSpellEvent extends PlayCardEvent {
    public CastSpellEvent(MTGGame game,
                          Player source,
                          Card c) {
        super(game, source, c);
    }

    @Override
    public boolean resolve() {
        //TODO: this is not how you do it in reality
        // need to put it on the stack, then ask them to pay costs
        // if they cant pay costs then revert everything
        game.data.getZone(c).remove(c);
        game.data.theStack.push(c);

        return true;
    }
}
