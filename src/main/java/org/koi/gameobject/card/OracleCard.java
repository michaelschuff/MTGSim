package org.koi.gameobject.card;

import org.koi.gameobject.cost.ManaCost;
import org.koi.util.Color;
import org.koi.gameobject.typeline.Typeline;

import java.util.UUID;

// represents an actual printed MTG card
public class OracleCard {
    public final String name;
    public final int power;
    public final int toughness;
    public final Color color;
    public final Typeline typeline;
    public final ManaCost manaCost;
    public final OracleCardAbilities abilities;
    public final UUID id = UUID.randomUUID();

    public OracleCard(String name,
                      int power,
                      int toughness,
                      Color color,
                      Typeline typeline,
                      ManaCost manaCost,
                      OracleCardAbilities abilities){
        this.name = name;
        this.power = power;
        this.toughness = toughness;
        this.color = color;
        this.typeline = typeline;
        this.manaCost = manaCost;
        this.abilities = abilities;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OracleCard other = (OracleCard) obj;
        return id.equals(other.id);
    }
}
