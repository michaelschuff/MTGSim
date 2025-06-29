package org.koi.event.combat;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.GameObjectOrPlayer;

import java.util.List;

public class AttackerAssignCombatDamageEvent extends AssignCombatDamageEvent {

    public AttackerAssignCombatDamageEvent(MTGGame game,
                                           Card source,
                                           List<GameObjectOrPlayer> targets,
                                           List<Integer> damage_assignment) {
        super(game, source, targets, damage_assignment);
    }

    @Override
    public boolean resolve() {
        // TODO: check trample and protection and deathtouch and stuff?
        //  maybe that needs to be checked beforehand.
        return true;
    }
}
