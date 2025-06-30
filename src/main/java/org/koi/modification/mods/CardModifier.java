package org.koi.modification.mods;


import org.koi.gameobject.card.Card;
import org.koi.modification.Modifier;


public abstract class CardModifier extends Modifier<Card> {
    public CardModifier() { }
    @Override
    public Card apply(Card original) {
        original.setCopiableValues(this.getValue());
        return original;
    }
}
