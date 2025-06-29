package org.koi.event.zonechange;


import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.Player;

public class DrawCardEvent extends ObjectMoveZoneEvent {
    public Player player;

    public DrawCardEvent(MTGGame game,
                         Player player) {
        super(game,
                null, null,
                player.data.library,
                player.data.hand);
        this.player = player;
    }
    public DrawCardEvent(MTGGame game,
                         Card source,
                         Player player) {
        super(game,
                source.asGameObjectOrPlayer(), null,
                player.data.library,
                player.data.hand);
        this.player = player;
    }

    @Override
    public boolean resolve() {
        if (player.data.library.isEmpty()) {
            player.data.drewFromEmptyLibraryFlag = true;
            return false;
        }

        Card obj = player.data.library.pop();
        player.data.hand.push(obj);

        return true;
    }
}
