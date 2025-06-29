package org.koi.event;

import org.koi.event.turn.TemporalEvent;
import org.koi.game.MTGGame;

import java.util.*;

public class EventDispatcher {
    public final MTGGame game;
    private final HashMap<Class<? extends Event>, List<EventListener>> triggeredAbilityListeners;
    private final HashMap<Class<? extends Event>, List<EventListener>> turnBasedActionListeners;

    public EventDispatcher(MTGGame game) {
        this.game = game;
        triggeredAbilityListeners = new HashMap<>();
        turnBasedActionListeners = new HashMap<>();
    }

    public <T extends TemporalEvent> void addTurnBasedActionListener(Class<T> eventType, EventListener listener) {
        turnBasedActionListeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    public <T extends Event> void addTriggeredAbilityListener(Class<T> eventType, EventListener listener) {
        triggeredAbilityListeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    public void dispatch(Event event) {
        if (this.turnBasedActionListeners.containsKey(event.getClass())) {
            for (EventListener listener : this.turnBasedActionListeners.get(event.getClass())) {
                listener.accept(game, event);
            }
        }
        if (this.triggeredAbilityListeners.containsKey(event.getClass())) {
            for (EventListener listener : this.triggeredAbilityListeners.get(event.getClass())) {
                listener.accept(game, event);
            }
        }
    }

    public void clearTriggeredAbilityListeners() {
        triggeredAbilityListeners.clear();
    }




}
