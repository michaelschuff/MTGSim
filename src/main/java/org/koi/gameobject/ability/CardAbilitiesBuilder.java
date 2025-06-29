package org.koi.gameobject.ability;

import org.koi.gameobject.card.CardAbilities;

import java.util.ArrayList;
import java.util.List;

public class CardAbilitiesBuilder {
    private final List<ActivatedAbility> activatedAbilities = new ArrayList<>();
    private final List<SpellAbility> spellAbilities = new ArrayList<>();
    private final List<TriggeredAbility> triggeredAbilities = new ArrayList<>();
    private final List<StaticAbility> staticAbilities = new ArrayList<>();

    public CardAbilitiesBuilder addAbility(Ability a) {
        if (a instanceof ActivatedAbility) {
            activatedAbilities.add((ActivatedAbility) a);
        } else if (a instanceof SpellAbility) {
            spellAbilities.add((SpellAbility) a);
        } else if (a instanceof TriggeredAbility) {
            triggeredAbilities.add((TriggeredAbility) a);
        } else if (a instanceof StaticAbility) {
            staticAbilities.add((StaticAbility) a);
        }
        return this;
    }
    public CardAbilitiesBuilder addAbilities(List<?> abilities) {
        for (Object o : abilities) {
            if (o instanceof Ability) {
                addAbility((Ability) o);
            }
        }
        return this;
    }

    public CardAbilities build() {
        return new CardAbilities(activatedAbilities, spellAbilities, triggeredAbilities, staticAbilities);
    }
}
