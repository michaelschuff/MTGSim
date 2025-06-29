package org.koi.util;


import org.koi.mana.ManaPool;
import org.koi.zone.Graveyard;
import org.koi.zone.Hand;
import org.koi.zone.Library;

public class PlayerData {
    public final Hand hand = new Hand();
    public final Library library = new Library();
    public final Graveyard graveyard = new Graveyard();

    public ManaPool manaPool = new ManaPool();

    public int lifeTotal = 40;
    public int startingLifeTotal = 40;
    public int maxHandSize = 7;

    public int landDropsPerTurn = 1;
    public int landsPlayedThisTurn = 0;


    public boolean drewFromEmptyLibraryFlag = false;

    public boolean hasLost = false;


    public PlayerData() {

    }

}
