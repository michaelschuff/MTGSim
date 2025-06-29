package org.koi.event.combat;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.GameObjectOrPlayer;

import java.util.List;

public class BlockerAssignCombatDamageEvent extends AssignCombatDamageEvent {

    public BlockerAssignCombatDamageEvent(MTGGame game,
                                          Card source,
                                          List<GameObjectOrPlayer> targets,
                                          List<Integer> damage_assignment) {
        super(game, source, targets, damage_assignment);
    }

    @Override
    public boolean resolve() {
        // TODO: check trample and protection and deathtouch and stuff?
        //  maybe that needs to be checked beforehand.
        //  and also create events for dealing damage after
        return true;
    }
}
