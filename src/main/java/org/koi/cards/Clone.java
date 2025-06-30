package org.koi.cards;

import org.koi.event.CreateContinuousEffectEvent;
import org.koi.event.Event;
import org.koi.event.zonechange.ObjectMoveZoneEvent;
import org.koi.game.MTGGame;
import org.koi.game.zone.AbstractZone;
import org.koi.gameobject.ability.ContinuousEffect;
import org.koi.gameobject.ability.OracleCardAbilitiesBuilder;
import org.koi.gameobject.ability.StaticReplacementAbility;
import org.koi.modification.Modification;
import org.koi.util.Color;
import org.koi.gameobject.ability.StaticModifierAbility;
import org.koi.gameobject.card.Card;
import org.koi.gameobject.card.OracleCard;
import org.koi.gameobject.cost.ManaCostBuilder;
import org.koi.gameobject.mana.ManaSymbol;
import org.koi.modification.ModificationBuilder;
import org.koi.modification.mods.CardModifier;
import org.koi.gameobject.typeline.TypelineBuilder;
import org.koi.util.GameObjectOrPlayer;
import org.koi.util.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public abstract class Clone {
    public static OracleCard getCard(MTGGame game) {
        return new OracleCard(
                "Clone",
                0, 0,
                Color.BLUE,
                new TypelineBuilder().creature().shapeshifter().build(),
                new ManaCostBuilder().add(ManaSymbol.THREE).add(ManaSymbol.BLUE).build(),
                new OracleCardAbilitiesBuilder().addStaticAbility(
                        (card) -> new StaticReplacementAbility(game, card) {
                            @Override
                            public boolean conditionalSuffix() {
                                return true;
                            }

                            @Override
                            public boolean isActiveCondition() {
                                // ability is always active, clone can enter the
                                // battlefield from anywhere as a copy of something
                                return true;
                            }

                            public boolean shouldReplace(Event e) {
                                if (e instanceof ObjectMoveZoneEvent) {
                                    ObjectMoveZoneEvent omze = (ObjectMoveZoneEvent) e;
                                    if (omze.c == card && omze.to_zone == game.data.battlefield) {
                                        return true;
                                    }
                                }
                                return false;
                            }

                            @Override
                            public boolean shouldReplace(List<Event> events) {
                                for (Event e : events) {
                                    if (shouldReplace(e)) {
                                        return true;
                                    }
                                }
                                return false;
                            }

                            @Override
                            public List<Event> replace(List<Event> events) {
                                List<Card> options = game.data.battlefield.stream()
                                        .filter((c) -> c.typeline.isType("Creature") && c.status.phasedIn)
                                        .collect(Collectors.toList());
                                Player p = source.controller;
                                int choice_index = game.getPlayerInputChoice(
                                        p, "Choose a creature to enter as a copy of:",
                                        options, true);

                                List<Event> newEvents = new ArrayList<>(events);
                                ListIterator<Event> iter = events.listIterator();
                                int numReplaced = 0;
                                while (iter.hasNext()) {
                                    if (shouldReplace(iter.next())) {
                                        newEvents.add(
                                                iter.nextIndex() + numReplaced,
                                                new CreateContinuousEffectEvent(game) {
                                            @Override
                                            public ContinuousEffect createEffect() {
                                                return new ContinuousEffect(
                                                        game, card,
                                                        new ModificationBuilder(game).addCloneMod(
                                                                new CardModifier() {
                                                                    @Override
                                                                    protected Card getValue() {
                                                                        return options.get(choice_index);
                                                                    }
                                                                }
                                                        ).build()
                                                ) {
                                                    @Override
                                                    public List<Card> getSubjects() {
                                                        return List.of(card);
                                                    }
                                                };
                                            }
                                        });
                                        numReplaced++;
                                    }
                                }
                                return newEvents;
                            }
                        }
                ).build()
        );
    }
}
