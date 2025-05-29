package org.koi.gameobject.ability;

import org.koi.gameobject.Card;
import org.koi.modification.Modification;
import org.koi.util.OID;

import java.util.List;
import java.util.function.Function;

public class StaticModifierAbility extends StaticAbility {
    public final Modification mod;
    public final Function<Integer, Boolean> condition;
    public final Function<Integer, List<OID>> filter;

    public StaticModifierAbility(Modification mod,
                                 Function<Integer, Boolean> condition,
                                 Function<Integer, List<OID>> filter) {
        this.mod = mod;
        this.condition = condition;
        this.filter = filter;
    }
}
