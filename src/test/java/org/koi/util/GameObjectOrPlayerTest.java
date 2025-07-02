package org.koi.util;

import org.junit.Test;
import org.koi.cards.CardLoader;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;

import static org.junit.Assert.*;

public class GameObjectOrPlayerTest {

    @Test
    public void isGameObject() {
        MTGGame game = new MTGGame(Variant.Commander);
        Player player = new Player(0);
        GameObjectOrPlayer o = new GameObjectOrPlayer(new Card(
                CardLoader.loadCard(game, "Forest"),
                player,
                0
        ));
        GameObjectOrPlayer p = new GameObjectOrPlayer(player);

        assert o.isGameObject();
        assert !p.isGameObject();
    }

    @Test
    public void isPlayer() {
        MTGGame game = new MTGGame(Variant.Commander);
        Player player = new Player(0);
        GameObjectOrPlayer o = new GameObjectOrPlayer(new Card(
                CardLoader.loadCard(game, "Forest"),
                player,
                0
        ));
        GameObjectOrPlayer p = new GameObjectOrPlayer(player);

        assert !o.isPlayer();
        assert p.isPlayer();
    }
}