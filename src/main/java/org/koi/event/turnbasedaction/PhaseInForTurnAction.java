package org.koi.event.turnbasedaction;

import org.koi.event.Event;
import org.koi.event.EventListener;
import org.koi.event.status.PhaseInCardEvent;
import org.koi.event.turn.beginning.UntapStepBeginEvent;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.CARD_STATUS_TYPE;
import org.koi.gameobject.card.Card;

import java.util.List;

public abstract class PhaseInForTurnAction {
    public static Class<UntapStepBeginEvent> type = UntapStepBeginEvent.class;
    public static EventListener turnBasedAction = new EventListener() {
        @Override
        public void accept(MTGGame game, Event event) {
            List<Card> cards = game.data.getCardsByStatus(CARD_STATUS_TYPE.PHASED_IN, false);
            for (Card c : cards) {
                game.eventManager.addEvent(new PhaseInCardEvent(game, null, c));
            }
        }
    };
}
