package org.koi.event;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.GameObjectOrPlayer;
import org.koi.util.Player;

public abstract class Event {
    // draw a card and gain 2 life
    // this event will draw a card,
    // 'next' event will gain 2 life
    public GameObjectOrPlayer source;
    public final MTGGame game;

    public Event(MTGGame game) {
        this.game = game;
        this.source = null;
    }
    public Event(MTGGame game, Card source) {
        this.game = game;
        this.source = new GameObjectOrPlayer(source);
    }
    public Event(MTGGame game, GameObjectOrPlayer source) {
        this.game = game;
        this.source = source;
    }
    public Event(MTGGame game, Player source) {
        this.game = game;
        this.source = new GameObjectOrPlayer(source);
    }

    public abstract boolean resolve();

    
}
