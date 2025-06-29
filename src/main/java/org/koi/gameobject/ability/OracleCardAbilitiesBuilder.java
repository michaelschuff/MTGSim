package org.koi.gameobject.ability;

import org.koi.gameobject.card.Card;
import org.koi.gameobject.card.OracleCardAbilities;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class OracleCardAbilitiesBuilder {
    private final List<Function<Card, ActivatedAbility>> activatedAbilities = new ArrayList<>();
    private final List<Function<Card, SpellAbility>> spellAbilities = new ArrayList<>();
    private final List<Function<Card, TriggeredAbility>> triggeredAbilities = new ArrayList<>();
    private final List<Function<Card, StaticAbility>> staticAbilities = new ArrayList<>();

    public OracleCardAbilitiesBuilder addActivatedAbility(Function<Card, ActivatedAbility> a) {
        activatedAbilities.add(a);
        return this;
    }
    public OracleCardAbilitiesBuilder addSpellAbility(Function<Card, SpellAbility> a) {
        spellAbilities.add(a);
        return this;
    }
    public OracleCardAbilitiesBuilder addTriggeredAbility(Function<Card, TriggeredAbility> a) {
        triggeredAbilities.add(a);
        return this;
    }
    public OracleCardAbilitiesBuilder addStaticAbility(Function<Card, StaticAbility> a) {
        staticAbilities.add(a);
        return this;
    }

    public OracleCardAbilities build() {
        return new OracleCardAbilities(activatedAbilities, spellAbilities, triggeredAbilities, staticAbilities);
    }
}
