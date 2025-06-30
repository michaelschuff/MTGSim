package org.koi.util;


import org.koi.gameobject.counters.CounterStore;
import org.koi.gameobject.mana.ManaPool;
import org.koi.game.zone.Graveyard;
import org.koi.game.zone.Hand;
import org.koi.game.zone.Library;

public class PlayerData {
    public final Hand hand = new Hand();
    public final Library library = new Library();
    public final Graveyard graveyard = new Graveyard();

    public ManaPool manaPool = new ManaPool();

    public int lifeTotal = 40;
    public int startingLifeTotal = 40;
    public int maxHandSize = 7;
    public CounterStore counters = new CounterStore();

    public int landDropsPerTurn = 1;
    public int landsPlayedThisTurn = 0;


    public boolean drewFromEmptyLibraryFlag = false;

    public boolean hasLost = false;


    public PlayerData() {

    }

}
