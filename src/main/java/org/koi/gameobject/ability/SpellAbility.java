package org.koi.gameobject.ability;

import org.koi.event.Event;
import org.koi.game.MTGGame;

import java.util.List;
import java.util.function.Function;

public class SpellAbility extends Ability {
    public List<Event> effects;
    public Function<MTGGame, Boolean> condition;
    public SpellAbility(MTGGame game,
                        List<Event> effects,
                        Function<MTGGame, Boolean> condition) {
        super(game);
        this.effects = effects;
        this.condition = condition;
    }
    public SpellAbility(MTGGame game,
                        List<Event> effects) {
        super(game);
        this.effects = effects;
        this.condition = (c) -> true;
    }


}
