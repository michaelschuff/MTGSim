package org.koi.gameobject.card;


import org.koi.cost.ManaCost;
import org.koi.gameobject.GameObject;
import org.koi.gameobject.PowTou;
import org.koi.util.Color;
import org.koi.gameobject.typeline.Typeline;
import org.koi.util.Player;

// represents a card as it exists in the game
// observes
public class Card extends GameObject {
    public final OracleCard original;
    public String name;
    public PowTou pt;
    public Color color;
    public Typeline typeline;
    public ManaCost manaCost;
    public CardStatus status;
    public CardAbilities abilities;

    public Player controller;
    public final Player owner;


    public Card(OracleCard original,
                Player creator,
                int timestamp) {
        super(timestamp);
        this.original = original;
        this.name = original.name;
        this.pt = new PowTou(original.power, original.toughness);
        this.color = original.color;
        this.typeline = original.typeline;
        this.manaCost = original.manaCost;
        this.status = new CardStatus();
        this.controller = creator;
        this.owner = creator;
        this.abilities = original.abilities.generate(this);
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
        this.controller = this.owner;
        this.color = original.color;
        this.typeline = original.typeline;
        this.manaCost = original.manaCost;
        this.abilities = original.abilities.generate(this);
    }


    @Override
    public String toString() {
        String s = name + ":";
        s += "\n\t     Owner:      " + owner;
        s += "\n\tController:      " + controller;
        s += "\n\t     Color:      " + color.toString();
        s += "\n\t  Typeline:      " + typeline.toString();
        s += "\n\t Mana Cost:      " + manaCost.toString();
        s += "\n\t       P/T:      " + pt.toString();
        s += "\n\t    status:      " + status.toString();
        s += "\n";
        return s;
    }
}
