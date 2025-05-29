package org.koi.gameobject;


import org.koi.util.Color;
import org.koi.gameobject.ability.Ability;
import org.koi.mana.ManaCost;
import org.koi.gameobject.typeline.Typeline;
import org.koi.util.OID;

import java.util.List;

// represents a card as it exists in the game
// observes
public class Card extends GameObject {
    public final MTGCard original;
    public String name;
    public PowTou pt;
    public int controller;
    public Color color;
    public Typeline typeline;
    public ManaCost manaCost;
    public List<Ability> abilities;
    public OID id;
    public CardStatus status;


    public Card(MTGCard source, int controller, OID id, int timestamp) {
        super(timestamp);
        this.original = source;
        this.name = source.name;
        this.pt = new PowTou(source.power, source.toughness);
        this.controller = controller;
        this.color = source.color;
        this.typeline = source.typeline;
        this.manaCost = source.manaCost;
        this.abilities = source.abilities;
        this.id = id;
        this.status = new CardStatus();
    }
    public void setCopiableValues(Card other) {
        this.name = other.name;
        this.pt = new PowTou(other.pt);
        this.color = other.color;
        this.typeline = other.typeline;
        this.manaCost = other.manaCost;
        this.abilities = other.abilities;
    }
    public void reset() {
        this.name = original.name;
        this.pt = new PowTou(original.power, original.toughness);
        this.controller = original.owner;
        this.color = original.color;
        this.typeline = original.typeline;
        this.manaCost = original.manaCost;
        this.abilities = original.abilities;
    }

    @Override
    public String toString() {
        String s = name + ":";
//        s += "\n\tController:      " + controller;
        s += "\n\t     Color:      " + color.toString();
        s += "\n\t  Typeline:      " + typeline.toString();
        s += "\n\t Mana Cost:      " + manaCost.toString();
        s += "\n\t       P/T:      " + pt.toString();
        return s + "\n";
    }
}
