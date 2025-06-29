package org.koi.turnbasedaction;

import org.koi.event.Event;
import org.koi.event.EventListener;
import org.koi.event.combat.DeclareBlockerEvent;
import org.koi.event.turn.TemporalEvent;
import org.koi.event.turn.combat.DeclareBlockersStepBeginEvent;
import org.koi.game.MTGGame;
import org.koi.gameobject.card.Card;
import org.koi.util.Player;

import java.util.List;
import java.util.stream.Collectors;

public abstract class DeclareBlockersTurnAction {
    public static Class<DeclareBlockersStepBeginEvent> type = DeclareBlockersStepBeginEvent.class;
    public static EventListener turnBasedAction = new EventListener() {
        @Override
        public void accept(MTGGame game, Event event) {
            Player ap = game.getActivePlayer();
            List<Card> attackers = game.data.battlefield.stream()
                    .filter((c) -> c.status.attacking && c.status.phasedIn)
                    .collect(Collectors.toList());
            List<Card> valid_blockers = game.data.battlefield.stream()
                    .filter((c) -> c.controller == ap && !c.status.tapped && c.status.phasedIn)
                    .collect(Collectors.toList());
            int blocker_choice;
            while (true) {
                blocker_choice = game.getPlayerInputChoice(
                        ap,"Choose a creature to block with",
                        valid_blockers, true);
                if (blocker_choice != -1) {
                    int attacker_choice = game.getPlayerInputChoice(
                            ap, "Choose an attacking creature to block",
                            attackers, false);
                    game.eventManager.addEvent(
                            new DeclareBlockerEvent(
                                    game,
                                    valid_blockers.get(blocker_choice),
                                    List.of(attackers.get(attacker_choice))
                            )
                    );
                } else {
                    break;
                }
            }

        }
    };

    public static Class<? extends TemporalEvent> getType() {
        if (type == null) type = DeclareBlockersStepBeginEvent.class;
        return type;
    }
}
