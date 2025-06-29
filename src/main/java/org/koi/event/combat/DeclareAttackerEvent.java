package org.koi.event.combat;

import org.koi.game.MTGGame;
import org.koi.event.Event;
import org.koi.gameobject.card.Card;
import org.koi.util.GameObjectOrPlayer;

public class DeclareAttackerEvent extends Event {
    public GameObjectOrPlayer target;
    public DeclareAttackerEvent(MTGGame game,
                                Card source,
                                GameObjectOrPlayer target) {
        super(game, source);
        this.target = target;
    }

    @Override
    public boolean resolve() {
        if (source.isGameObject()) {
            if (source.o instanceof Card) {
                Card c = (Card) source.o;
                if (!c.status.tapped) {
                    c.status.tapped = true;
                    c.status.attacking = true;
                    c.status.attackTarget = target;
                    // TODO: protection and stuff
                    // TODO: create event to tap if needed
                    // TODO: create event for on attacks
                    return true;
                }
            }
        }
        return false;
    }
}
