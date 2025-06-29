package org.koi.event;


import org.koi.game.MTGGame;

public interface EventListener {
    /**
     * Invoked when an event is dispatched in the AWT.
     * @param event the event to be processed
     */
    void accept(MTGGame game, Event event);
}