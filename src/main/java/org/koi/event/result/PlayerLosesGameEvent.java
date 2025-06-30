package org.koi.event.result;

import org.koi.event.Event;
import org.koi.game.MTGGame;
import org.koi.gameobject.ability.ContinuousEffect;
import org.koi.gameobject.ability.StackAbility;
import org.koi.gameobject.card.Card;
import org.koi.util.Player;

import java.util.Stack;

public class PlayerLosesGameEvent extends Event {
    public GameLossReason reason;
    public Player player;
    public PlayerLosesGameEvent(MTGGame game, Player player, GameLossReason reason) {
        super(game);
        this.player = player;
        this.reason = reason;
    }
    @Override
    public boolean resolve() {
        /**
         * 800.4a.
         * When a player leaves the game, all objects (see rule 109)
         *     owned by that player leave the game
         * Any effects which give that player control of any objects or players end.
         * Then, if that player controlled any objects on the stack not represented by
         *     cards, those objects cease to exist.
         * // TODO: Then, if there are any objects still controlled by that player, those objects are
         *     exiled.
         *       note: This is not a state-based action. It happens as soon as
         *       the player leaves the game. If the player who left the game had
         *       priority at the time they left, priority passes to the next player
         *       in turn order who's still in the game.
         */
        game.data.battlefield.removeIf(c -> c.owner == player);
        game.data.commandZone.removeIf(c -> c.owner == player);
        game.data.exile.removeIf(c -> c.owner == player);
        game.data.theStack.removeIf(o -> {
            if (o instanceof Card) {
                return ((Card) o).owner == player;
            }
            return false;
        });
        player.data.hand.removeAllElements();
        player.data.graveyard.removeAllElements();
        player.data.library.removeAllElements();

        game.data.continuousEffects.removeIf(ce -> {
            if (ce.effect.controllerModifier != null) {
                for (Card c : ce.getSubjects()) {
                    //TODO: this doesnt garuntee that *it* is the effect giving control to player
                    // just that its changing the controller and the new controller is the player
                    // to fix: apply the modifier and see what happens
                    if (c.controller != c.owner && c.controller == player && ce.effect.controllerModifier != null) {
                        return true;
                    }
                }
            }
            return false;
        });

        game.data.theStack.removeIf(o -> {
            if (o instanceof StackAbility) {
                return ((StackAbility) o).controller == player;
            }
            return false;
        });


        //If there are any objects still controlled by that player, exile them here





        player.data.hasLost = true;
        game.turnController.PlayerLoss(player);
        return true;
    }
}
