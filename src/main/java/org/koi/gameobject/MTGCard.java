package org.koi.gameobject;

import org.koi.util.Color;
import org.koi.gameobject.ability.Ability;
import org.koi.mana.ManaCost;
import org.koi.gameobject.typeline.Typeline;

import java.util.ArrayList;
import java.util.List;

// represents an actual printed MTG card
public class MTGCard {
    public final String name;
    public final int power;
    public final int toughness;
    public final Color color;
    public final Typeline typeline;
    public final ManaCost manaCost;
    public final List<Ability> abilities;
    public final int owner;

    public MTGCard(String name,
                   int power,
                   int toughness,
                   Color color,
                   Typeline typeline,
                   ManaCost manaCost,
                   List<Ability> abilities,
                   int owner){
        this.name = name;
        this.power = power;
        this.toughness = toughness;
        this.color = color;
        this.typeline = typeline;
        this.manaCost = manaCost;
        this.abilities = abilities;
        this.owner = owner;
    }
    public MTGCard(Card c) {
        this.name = c.name;
        this.power = c.pt.power();
        this.toughness = c.pt.toughness();
        this.color = c.color;
        this.typeline = c.typeline;
        this.manaCost = c.manaCost;
        this.abilities = new ArrayList<>(c.abilities);
        this.owner = c.original.owner;
    }
}
