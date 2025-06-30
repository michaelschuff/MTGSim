package org.koi.event.statebasedactions;

import org.koi.event.Event;
import org.koi.event.EventListener;
import org.koi.game.MTGGame;

public abstract class WorldRuleSBA {
    public static EventListener get() {
        return new EventListener() {
            @Override
            public void accept(MTGGame game, Event event) {
                /**
                 * 704.5k.
                 * If two or more permanents have the supertype world, all except
                 * the one that has had the world supertype for the shortest amount
                 * of time are put into their owners' graveyards. In the event of a
                 * tie for the shortest amount of time, all are put into their owners'
                 * graveyards. This is called the "world rule."
                 */
                // TODO: IDK if this is referring to timestamps or not.
                //  I think not, so need a new system to keep track of this
            }
        };
    }
}
