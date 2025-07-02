package org.koi.gameobject.card;

import org.koi.gameobject.ability.ActivatedAbility;
import org.koi.gameobject.ability.SpellAbility;
import org.koi.gameobject.ability.StaticAbility;
import org.koi.gameobject.ability.TriggeredAbility;

import java.util.ArrayList;
import java.util.List;

public class CardAbilities {
    public final List<ActivatedAbility> activatedAbilities;
    public final List<SpellAbility> spellAbilities;
    public final List<TriggeredAbility> triggeredAbilities;
    public final List<StaticAbility> staticAbilities;

    public CardAbilities(List<ActivatedAbility> activatedAbilities,
                         List<SpellAbility> spellAbilities,
                         List<TriggeredAbility> triggeredAbilities,
                         List<StaticAbility> staticAbilities) {
        this.activatedAbilities = activatedAbilities;
        this.spellAbilities = spellAbilities;
        this.triggeredAbilities = triggeredAbilities;
        this.staticAbilities = staticAbilities;
    }
    public CardAbilities() {
        this.activatedAbilities = new ArrayList<>();
        this.spellAbilities = new ArrayList<>();
        this.triggeredAbilities = new ArrayList<>();
        this.staticAbilities = new ArrayList<>();
    }

    public CardAbilities(CardAbilities ca) {
        this.activatedAbilities = new ArrayList<>(ca.activatedAbilities);
        this.spellAbilities = new ArrayList<>(ca.spellAbilities);
        this.triggeredAbilities = new ArrayList<>(ca.triggeredAbilities);
        this.staticAbilities = new ArrayList<>(ca.staticAbilities);
    }

    public void addAll(CardAbilities ca) {
        this.activatedAbilities.addAll(ca.activatedAbilities);
        this.spellAbilities.addAll(ca.spellAbilities);
        this.triggeredAbilities.addAll(ca.triggeredAbilities);
        this.staticAbilities.addAll(ca.staticAbilities);
    }

    public void removeAll(CardAbilities ca) {
        this.activatedAbilities.removeAll(ca.activatedAbilities);
        this.spellAbilities.removeAll(ca.spellAbilities);
        this.triggeredAbilities.removeAll(ca.triggeredAbilities);
        this.staticAbilities.removeAll(ca.staticAbilities);
    }

    public int size() {
        return activatedAbilities.size() + spellAbilities.size() + triggeredAbilities.size() + staticAbilities.size();
    }
}
