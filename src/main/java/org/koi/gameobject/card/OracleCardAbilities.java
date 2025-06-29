package org.koi.gameobject.card;

import org.koi.gameobject.ability.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class OracleCardAbilities {
    public final List<Function<Card, ActivatedAbility>> activatedAbilities;
    public final List<Function<Card, SpellAbility>> spellAbilities;
    public final List<Function<Card, TriggeredAbility>> triggeredAbilities;
    public final List<Function<Card, StaticAbility>> staticAbilities;

    public OracleCardAbilities(List<Function<Card, ActivatedAbility>> activatedAbilities,
                                List<Function<Card, SpellAbility>> spellAbilities,
                                List<Function<Card, TriggeredAbility>> triggeredAbilities,
                                List<Function<Card, StaticAbility>> staticAbilities) {
        this.activatedAbilities = activatedAbilities;
        this.spellAbilities = spellAbilities;
        this.triggeredAbilities = triggeredAbilities;
        this.staticAbilities = staticAbilities;
    }
    public OracleCardAbilities() {
        this.activatedAbilities = new ArrayList<>();
        this.spellAbilities = new ArrayList<>();
        this.triggeredAbilities = new ArrayList<>();
        this.staticAbilities = new ArrayList<>();
    }

    public OracleCardAbilities(OracleCardAbilities ca) {
        this.activatedAbilities = new ArrayList<>(ca.activatedAbilities);
        this.spellAbilities = new ArrayList<>(ca.spellAbilities);
        this.triggeredAbilities = new ArrayList<>(ca.triggeredAbilities);
        this.staticAbilities = new ArrayList<>(ca.staticAbilities);
    }




    public void addAll(OracleCardAbilities ca) {
        this.activatedAbilities.addAll(ca.activatedAbilities);
        this.spellAbilities.addAll(ca.spellAbilities);
        this.triggeredAbilities.addAll(ca.triggeredAbilities);
        this.staticAbilities.addAll(ca.staticAbilities);
    }

    public void removeAll(OracleCardAbilities ca) {
        this.activatedAbilities.removeAll(ca.activatedAbilities);
        this.spellAbilities.removeAll(ca.spellAbilities);
        this.triggeredAbilities.removeAll(ca.triggeredAbilities);
        this.staticAbilities.removeAll(ca.staticAbilities);
    }

    public CardAbilities generate(Card c) {
        CardAbilitiesBuilder b = new CardAbilitiesBuilder();
        b.addAbilities(activatedAbilities.stream().map((f) -> f.apply(c)).collect(Collectors.toList()));
        b.addAbilities(spellAbilities.stream().map((f) -> f.apply(c)).collect(Collectors.toList()));
        b.addAbilities(triggeredAbilities.stream().map((f) -> f.apply(c)).collect(Collectors.toList()));
        b.addAbilities(staticAbilities.stream().map((f) -> f.apply(c)).collect(Collectors.toList()));
        return b.build();
    }
}
