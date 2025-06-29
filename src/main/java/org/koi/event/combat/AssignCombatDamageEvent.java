package org.koi.event.combat;

import org.koi.event.Event;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.GameObjectOrPlayer;

import java.util.List;

public abstract class AssignCombatDamageEvent extends Event {
    public List<GameObjectOrPlayer> targets;
    public List<Integer> damage_assignment;
    public AssignCombatDamageEvent(MTGGame game,
                                   Card source,
                                   List<GameObjectOrPlayer> targets,
                                   List<Integer> damage_assignment) {
        super(game, source);
        this.targets = targets;
        this.damage_assignment = damage_assignment;
    }
}
