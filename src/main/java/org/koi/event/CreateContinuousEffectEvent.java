package org.koi.event;

import org.koi.game.MTGGame;
import org.koi.gameobject.ability.ContinuousEffect;

public abstract class CreateContinuousEffectEvent extends Event {

    public CreateContinuousEffectEvent(MTGGame game) {
        super(game);
    }

    @Override
    public boolean resolve() {
        ContinuousEffect ce = createEffect();
        game.data.addDisconnectedContinuousEffect(ce);
        return true;
    }

    public abstract ContinuousEffect createEffect();
}
