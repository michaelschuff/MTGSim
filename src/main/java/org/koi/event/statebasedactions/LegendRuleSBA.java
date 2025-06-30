package org.koi.event.statebasedactions;

import org.koi.event.Event;
import org.koi.event.EventListener;
import org.koi.event.zonechange.ObjectMoveZoneEvent;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.Player;

import java.util.List;
import java.util.stream.Collectors;

public abstract class LegendRuleSBA {
    public static EventListener get() {
        return new EventListener() {
            @Override
            public void accept(MTGGame game, Event event) {
                for (Player p : game.data.players) {
                    for (Card c : game.data.battlefield) {
                        if (c.typeline.isType("Legendary") && c.controller == p) {
                            List<Card> options = game.data.battlefield.stream()
                                    .filter(c1 -> c1.controller == p && c1.name.equals(c.name))
                                    .collect(Collectors.toList());

                            if (options.size() > 1) {
                                int choice = game.getPlayerInputChoice(
                                        p, "Choose a legendary permanent to keep",
                                        options, false
                                );

                                for (Card sac : options) {
                                    if (!sac.equals(options.get(choice))) {
                                        game.eventManager.addEvent(
                                                new ObjectMoveZoneEvent(
                                                        game, null, sac,
                                                        game.data.battlefield,
                                                        p.data.graveyard
                                                )
                                        );
                                    }
                                }

                                // other legend pairs will be removed by subsequent checks
                                return;

                            }
                        }
                    }
                }

            }
        };
    }
}
