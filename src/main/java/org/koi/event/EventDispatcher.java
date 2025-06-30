package org.koi.event;

import org.koi.event.turn.PlayerGainPriorityEvent;
import org.koi.event.turn.TemporalEvent;
import org.koi.event.turn.combat.DeclareAttackersStepBeginEvent;
import org.koi.game.MTGGame;

import java.util.*;

public class EventDispatcher {
    public final MTGGame game;
    public final HashMap<Class<? extends Event>, List<EventListener>> triggeredAbilityListeners;
    public final HashMap<Class<? extends Event>, List<EventListener>> turnBasedActionListeners;

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
        System.out.println("Dispatching event:" + event.getClass());
        turnBasedActionListeners.entrySet().stream()
                .filter( e -> e.getKey().isAssignableFrom(event.getClass()))
                .forEach( e -> {
                    for (EventListener listener : e.getValue()) {
                        listener.accept(game, event);
                    }
                });
        triggeredAbilityListeners.entrySet().stream()
                .filter( e -> e.getKey().isAssignableFrom(event.getClass()))
                .forEach( e -> {
                    for (EventListener listener : e.getValue()) {
                        listener.accept(game, event);
                    }
                });
    }

    public void clearTriggeredAbilityListeners() {
        triggeredAbilityListeners.clear();
    }




}
