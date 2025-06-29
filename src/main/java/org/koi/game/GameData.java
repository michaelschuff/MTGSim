package org.koi.game;

import org.koi.LAYER;
import org.koi.gameobject.*;
import org.koi.gameobject.ability.*;
import org.koi.gameobject.card.CARD_STATUS_TYPE;
import org.koi.gameobject.card.Card;
import org.koi.gameobject.card.OracleCard;
import org.koi.mana.Mana;
import org.koi.util.OID;
import org.koi.util.Player;
import org.koi.zone.*;

import java.util.*;
import java.util.stream.Collectors;


public class GameData {
    public final MTGGame game;
    public final Battlefield battlefield;
    public final MTGStack theStack;
    public final Exile exile;
    public final CommandZone commandZone;
    public final HashMap<OID, Card> cardMap;
    public final HashMap<OID, StackAbility> abilityMap;
    public final List<Player> players;
    public final TimestampGenerator timestampGen;


    public GameData(MTGGame game) {
        this.game = game;
        this.battlefield = new Battlefield();
        this.theStack = new MTGStack();
        this.exile = new Exile();
        this.commandZone = new CommandZone();

        this.timestampGen = new TimestampGenerator();

        this.cardMap = new HashMap<>();
        this.abilityMap = new HashMap<>();
        this.players = new ArrayList<>();
    }

    public void setLibraries(List<List<OracleCard>> decklists) {
        int timestamp = timestampGen.getNew();
        for (List<OracleCard> deck : decklists) {
            int i = this.players.size();
            this.players.add(new Player(i));

            for (OracleCard mtgCard : deck) {
                Card c = new Card(mtgCard, this.players.get(i), timestamp);
                this.players.get(i).data.library.push(c);
                this.cardMap.put(c.id, c);
            }
        }
    }

//    public SpellAbility getAbility(OID id) {
//        return abilityMap.get(id);
//    }
//
//    public Card getCard(OID id) {
//        return cardMap.get(id);
//    }

//    public void setCard(OID id, Card c) {
//        cardMap.remove(id);
//        cardMap.put(id, c);
//    }

    // returns null if object does not exist in a zone
    public AbstractZone getZone(Card c) {
        if (battlefield.contains(c)) return battlefield;
        if (theStack.contains(c)) return theStack;
        if (exile.contains(c)) return exile;
        if (commandZone.contains(c)) return commandZone;


        for (Player p : players)
            if (p.data.graveyard.contains(c))
                return p.data.graveyard;


        for (Player p : players)
            if (p.data.hand.contains(c))
                return p.data.hand;


        for (Player p : players)
            if (p.data.library.contains(c))
                return p.data.library;

        return null;
    }
    public ZONE getZoneType(Card c) {
        if (battlefield.contains(c)) return ZONE.BATTLEFIELD;
        if (theStack.contains(c)) return ZONE.STACK;
        if (exile.contains(c)) return ZONE.EXILE;
        if (commandZone.contains(c)) return ZONE.COMMAND;

        for (Player p : players)
            if (p.data.graveyard.contains(c))
                return ZONE.GRAVEYARD;


        for (Player p : players)
            if (p.data.hand.contains(c))
                return ZONE.HAND;


        for (Player p : players)
            if (p.data.library.contains(c))
                return ZONE.LIBRARY;

        return ZONE.NONE;
    }

//    public Player getOwner(OID id) {
//        return getCard(id).original.owner;
//    }

//    public Player getController(OID id) {
//        if (cardMap.containsKey(id)) {
//            return getCard(id).controller;
//        } else {
//            return getAbility(id).controller;
//        }
//    }

    public boolean isStackEmpty() {
        return theStack.isEmpty();
    }

    public List<Card> getCardsByStatus(CARD_STATUS_TYPE type, boolean value) {
        return battlefield.stream().filter((c) -> c.status.getStatus(type) == value).collect(Collectors.toList());
    }

    // TODO: only check valid replacement ability
    public List<StaticReplacementAbility> getReplacementEffects() {
        List<StaticReplacementAbility> ret = new ArrayList<>();
        for (Card c : battlefield) {
            for (StaticAbility a : c.abilities.staticAbilities) {
                if (a instanceof StaticReplacementAbility) {
                    StaticReplacementAbility sa = (StaticReplacementAbility) a;
                    ret.add(sa);
                }
            }
        }
        return ret;
    }

    private List<ContinuousEffect> getStaticEffects() {
        List<ContinuousEffect> ret = new ArrayList<>();
        for (Card c : battlefield) {
            for (StaticAbility a : c.abilities.staticAbilities) {
                if (a instanceof StaticModifierAbility) {
                    StaticModifierAbility sa = (StaticModifierAbility) a;
                    if (sa.condition.apply(c)) {
                        ContinuousEffect e = new ContinuousEffect(game, c, sa.mod, sa.filter);
                        ret.add(e);
                    }
                }
            }
        }
        return ret;
    }

    public void updateBoardState() {
        // TODO: copy from OldGameState
        // sort all effects into the first place they get applied
        getAndApplyContinuousEffects();
        registerListeners();

    }

    private void getAndApplyContinuousEffects() {
        List<ContinuousEffect> layer1a_StaticEffects = this.getStaticEffects();

        List<ContinuousEffect> layer2_StaticEffects = layer1a_StaticEffects.stream().filter((e) -> !e.effect.firstWorksOnLayer(LAYER.L1a)).collect(Collectors.toList());
        layer1a_StaticEffects.removeAll(layer2_StaticEffects);


        List<ContinuousEffect> layer4_StaticEffects = layer2_StaticEffects.stream().filter((e) -> !e.effect.firstWorksOnLayer(LAYER.L2)).collect(Collectors.toList());
        layer2_StaticEffects.removeAll(layer4_StaticEffects);

        List<ContinuousEffect> layer5_StaticEffects = layer4_StaticEffects.stream().filter((e) -> !e.effect.firstWorksOnLayer(LAYER.L4)).collect(Collectors.toList());
        layer4_StaticEffects.removeAll(layer5_StaticEffects);

        List<ContinuousEffect> layer6_StaticEffects = layer5_StaticEffects.stream().filter((e) -> !e.effect.firstWorksOnLayer(LAYER.L5)).collect(Collectors.toList());
        layer5_StaticEffects.removeAll(layer6_StaticEffects);

        List<ContinuousEffect> layer7a_StaticEffects = layer6_StaticEffects.stream().filter((e) -> !e.effect.firstWorksOnLayer(LAYER.L6)).collect(Collectors.toList());
        layer6_StaticEffects.removeAll(layer7a_StaticEffects);

        List<ContinuousEffect> layer7b_StaticEffects = layer7a_StaticEffects.stream().filter((e) -> !e.effect.firstWorksOnLayer(LAYER.L7a)).collect(Collectors.toList());
        layer7a_StaticEffects.removeAll(layer7b_StaticEffects);

        List<ContinuousEffect> layer7c_StaticEffects = layer7b_StaticEffects.stream().filter((e) -> !e.effect.firstWorksOnLayer(LAYER.L7b)).collect(Collectors.toList());
        layer7b_StaticEffects.removeAll(layer7c_StaticEffects);

        List<ContinuousEffect> layer7d_StaticEffects = layer7c_StaticEffects.stream().filter((e) -> !e.effect.firstWorksOnLayer(LAYER.L7c)).collect(Collectors.toList());
        layer7c_StaticEffects.removeAll(layer7d_StaticEffects);







        // LAYER 1a
        for (ContinuousEffect e : layer1a_StaticEffects) {
            e.selectAffectedObjects();
        }
        while (!layer1a_StaticEffects.isEmpty()) {
            sortContinuousEffects(layer1a_StaticEffects);

            ContinuousEffect e = layer1a_StaticEffects.remove(0);
            layer2_StaticEffects.add(e);
            e.applyToObjects(LAYER.L1a);
        }



        // LAYER 2
        for (ContinuousEffect e : layer2_StaticEffects) {
            e.selectAffectedObjects();
        }
        while (!layer2_StaticEffects.isEmpty()) {
            sortContinuousEffects(layer2_StaticEffects);

            ContinuousEffect e = layer2_StaticEffects.remove(0);
            layer4_StaticEffects.add(e);
            e.applyToObjects(LAYER.L2);
        }


        // LAYER 4
        for (ContinuousEffect e : layer4_StaticEffects) {
            e.selectAffectedObjects();
        }
        while (!layer4_StaticEffects.isEmpty()) {
            sortContinuousEffects(layer4_StaticEffects);

            ContinuousEffect e = layer4_StaticEffects.remove(0);
            layer5_StaticEffects.add(e);
            e.applyToObjects(LAYER.L4);
        }

        //LAYER 5
        for (ContinuousEffect e : layer5_StaticEffects) {
            e.selectAffectedObjects();
        }
        while (!layer5_StaticEffects.isEmpty()) {
            sortContinuousEffects(layer5_StaticEffects);

            ContinuousEffect e = layer5_StaticEffects.remove(0);
            layer6_StaticEffects.add(e);
            e.applyToObjects(LAYER.L5);
        }

        //LAYER 6
        for (ContinuousEffect e : layer6_StaticEffects) {
            e.selectAffectedObjects();
        }
        while (!layer6_StaticEffects.isEmpty()) {
            sortContinuousEffects(layer6_StaticEffects);

            ContinuousEffect e = layer6_StaticEffects.remove(0);
            layer7a_StaticEffects.add(e);
            e.applyToObjects(LAYER.L6);
        }


        //LAYER 7a
        for (ContinuousEffect e : layer7a_StaticEffects) {
            e.selectAffectedObjects();
        }
        while (!layer7a_StaticEffects.isEmpty()) {
            sortContinuousEffects(layer7a_StaticEffects);

            ContinuousEffect e = layer7a_StaticEffects.remove(0);
            layer7b_StaticEffects.add(e);
            e.applyToObjects(LAYER.L7a);
        }
        //LAYER 7b
        for (ContinuousEffect e : layer7b_StaticEffects) {
            e.selectAffectedObjects();
        }
        while (!layer7b_StaticEffects.isEmpty()) {
            sortContinuousEffects(layer7b_StaticEffects);

            ContinuousEffect e = layer7b_StaticEffects.remove(0);
            layer7c_StaticEffects.add(e);
            e.applyToObjects(LAYER.L7b);
        }

        //LAYER 7c
        for (ContinuousEffect e : layer7c_StaticEffects) {
            e.selectAffectedObjects();
        }
        while (!layer7c_StaticEffects.isEmpty()) {
            sortContinuousEffects(layer7c_StaticEffects);

            ContinuousEffect e = layer7c_StaticEffects.remove(0);
            layer7d_StaticEffects.add(e);
            e.applyToObjects(LAYER.L7c);
        }



        //LAYER 7d
        for (ContinuousEffect e : layer7d_StaticEffects) {
            e.selectAffectedObjects();
        }
        while (!layer7d_StaticEffects.isEmpty()) {
            sortContinuousEffects(layer7d_StaticEffects);

            ContinuousEffect e = layer7d_StaticEffects.remove(0);
            e.applyToObjects(LAYER.L7d);
        }
    }

    private void sortContinuousEffects(List<ContinuousEffect> effects) {
        // TODO: sort by timestamp and dependency
        //       garunteed to be applied in same layer
    }


    private void registerListeners() {
        for (Card c : cardMap.values()) {
            for (TriggeredAbility ta : c.abilities.triggeredAbilities) {
                game.eventManager.dispatcher.addTriggeredAbilityListener(ta.triggerType, ta.listener);
            }
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder("Current Game State:");
        s.append("\n\t Battlefield:");
        if (battlefield.isEmpty()) s.append(" <empty>");
        for (Card c : battlefield) s.append("\n\t\t").append(c.name);

        s.append("\n\t Stack:");
        if (theStack.isEmpty()) s.append(" <empty>");
        for (GameObject o : theStack) {
            if (o instanceof Card) {
                Card c = (Card) o;
                s.append("\n\t\t").append(c.name);
            } else if (o instanceof StackAbility) {
                StackAbility sa = (StackAbility) o;
                s.append("\n\t\t").append(sa.source.name).append(" Ability");
            }
        }
        for (Player p : players) {
            s.append("\n\t Player ").append(p.index).append(" (").append(p.data.lifeTotal).append(" life):");

            s.append("\n\t\tHand:");
            if (p.data.hand.isEmpty()) s.append(" <empty>");
            for (Card c : p.data.hand) s.append("\n\t\t\t").append(c.name);

            s.append("\n\t\tGraveyard:");
            if (p.data.graveyard.isEmpty()) s.append(" <empty>");
            for (Card c : p.data.graveyard) s.append("\n\t\t\t").append(c.name);

            s.append("\n\t\tManaPool:");
            if (p.data.manaPool.isEmpty()) s.append(" <empty>");
            for (Mana m : p.data.manaPool) s.append(m.color.toString());
        }
        return s.toString();
    }
}
