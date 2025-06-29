package org.koi.modification.mods;

import org.koi.gameobject.card.CardAbilities;
import org.koi.modification.Modifier;

public abstract class CardAbilitiesModifier extends Modifier<CardAbilities> {
    private final MOD_TYPE mode;

    protected CardAbilitiesModifier(MOD_TYPE m) {
        this.mode = m;
    }
    @Override
    public CardAbilities apply(CardAbilities original) {
        CardAbilities v = this.getValue();
        switch (this.mode) {
            case OVERRIDE: {
                return v;
            }
            case ADD: {
                CardAbilities ret = new CardAbilities(original);
                ret.addAll(v);
                return ret;
            }
            case SUBTRACT: {
                CardAbilities ret = new CardAbilities(original);
                ret.removeAll(v);
                return ret;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + this.mode);
        }
    }
}
