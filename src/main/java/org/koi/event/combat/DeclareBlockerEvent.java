package org.koi.event.combat;

import org.koi.event.Event;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;

import java.util.List;

public class DeclareBlockerEvent extends Event {
    public List<Card> targets;
    public DeclareBlockerEvent(MTGGame game,
                               Card source,
                               List<Card> targets) {
        super(game, source);
        this.targets = targets;
    }

    @Override
    public boolean resolve() {
        if (source.isGameObject()) {
            if (source.o instanceof Card) {
                Card c = (Card) source.o;
                if (!c.status.tapped && c.status.phasedIn) {
                    c.status.blocking = true;
                    c.status.inCombatWith = targets;
                    for (Card attacker : targets)
                        attacker.status.inCombatWith.add(c);

                    // TODO: protection and stuff
                    // TODO: create event to tap if needed
                    // TODO: create event for on blocks
                    return true;
                }
            }
        }
        return false;
    }
}
