package org.koi.event.turnbasedaction;

import org.koi.event.Event;
import org.koi.event.EventListener;
import org.koi.event.turn.end.CleanupStepBeginEvent;
import org.koi.event.zonechange.DiscardCardEvent;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.Player;


public abstract class DiscardToHandSizeTurnAction {
    public static Class<CleanupStepBeginEvent> type = CleanupStepBeginEvent.class;
    public static EventListener turnBasedAction = new EventListener() {
        @Override
        public void accept(MTGGame game, Event event) {
            Player ap = game.getActivePlayer();

            int diff = ap.data.hand.size() - ap.data.maxHandSize;
            while (diff > 0) {
                String prompt = "Please discard a card:";
                int choice_index = game.getPlayerInputChoice(ap, prompt, ap.data.hand, false);
                Card c = ap.data.hand.get(choice_index);
                game.eventManager.addEvent(new DiscardCardEvent(game, c));
                diff--;
            }
        }
    };
}
