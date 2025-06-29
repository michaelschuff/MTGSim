package org.koi.gameobject.ability;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.gameobject.GameObject;
import org.koi.util.Player;

public class StackAbility extends GameObject {
    public Player controller;
    public Player owner;
    public Ability ability_source;
    public Card source;

    public StackAbility(MTGGame game,
                        Card source,
                        Player owner,
                        Player controller,
                        Ability ability_source) {
        super(game.data.timestampGen.getNew());
        this.source = source;
        this.owner = owner;
        this.controller = controller;
        this.ability_source = ability_source;
    }
}
