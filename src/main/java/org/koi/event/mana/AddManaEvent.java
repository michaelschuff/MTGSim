package org.koi.event.mana;

import org.koi.event.Event;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.gameobject.mana.Mana;
import org.koi.util.Player;

import java.util.ArrayList;
import java.util.List;

public class AddManaEvent extends Event {
    public List<Mana> mana = new ArrayList<>();
    public Card source;
    public Player p;
    public AddManaEvent(MTGGame game,
                        Card source,
                        Player p,
                        List<Mana> mana) {
        super(game);
        this.mana.addAll(mana);
        this.p = p;
        this.source = source;

    }
    public AddManaEvent(MTGGame game,
                        Card source,
                        Player p,
                        Mana mana) {
        super(game);
        this.mana.add(mana);
        this.p = p;
        this.source = source;
    }

    @Override
    public boolean resolve() {
        p.data.manaPool.addAll(mana);
        return true;
    }
}
