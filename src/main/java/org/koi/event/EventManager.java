package org.koi.event;

import org.koi.game.MTGGame;
import org.koi.gameobject.ability.StaticReplacementAbility;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    public final MTGGame game;
    public EventDispatcher dispatcher;
    public List<Event> events;

    public EventManager(MTGGame game) {
        this.game = game;
        dispatcher = new EventDispatcher(game);
        this.events = new ArrayList<>();
    }

    public void addEvents(List<Event> events) {
        this.events.addAll(events);
    }

    public void addEvent(Event e) {
//        System.out.println("Event added: " + e.getClass().getCanonicalName());
        this.events.add(e);
    }


    public void resolveEvents() {
        List<Event> events_copy = events;
        events = new ArrayList<>();
        for (Event e : events_copy) {
            // resolve event
            // if event succeeded in resolving (wasn't stopped by something)
            // notify all listeners that event happened
            boolean success = e.resolve();
            if (success) {
                dispatcher.dispatch(e);
            }
//            game.data.updateBoardState();
        }
    }



    // TODO: This is way oversimplified
    public void applyReplacementEffects(List<StaticReplacementAbility> replacements) {
        for (Event e : events) {
            for (StaticReplacementAbility r : replacements) {
                if (r.originalType.isInstance(e)) {
                    e = r.replacement;
                }
            }
        }
    }


}
