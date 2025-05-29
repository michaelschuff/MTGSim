package org.koi;

import org.koi.gameobject.Card;
import org.koi.gameobject.MTGCard;
import org.koi.gameobject.TimestampGenerator;
import org.koi.util.OID;
import org.koi.gameobject.ability.Ability;
import org.koi.gameobject.ability.ContinuousEffect;
import org.koi.gameobject.ability.StaticModifierAbility;
import org.koi.turn.PhaseType;
import org.koi.zone.*;

import java.util.*;
import java.util.stream.Collectors;


public class GameData {
    public final Battlefield battlefield;
    public final MTGStack theStack;
    public final Exile exile;
    public final CommandZone commandZone;
    public final HashMap<OID, Card> cardMap;
    public final List<PlayerData> playerData;
    public final TimestampGenerator timestampGen;


    public GameData(List<List<MTGCard>> decklists) {
        this.battlefield = new Battlefield();
        this.theStack = new MTGStack();
        this.exile = new Exile();
        this.commandZone = new CommandZone();

        this.timestampGen = new TimestampGenerator();

        this.cardMap = new HashMap<>();
        this.playerData = new ArrayList<>();
        int timestamp = timestampGen.getNew();
        for (List<MTGCard> deck : decklists) {
            int i = this.playerData.size();
            this.playerData.add(new PlayerData());

            for (MTGCard mtgCard : deck) {
                OID id = new OID();
                Card c = new Card(mtgCard, i, id, timestamp);
                this.playerData.get(i).library.add(id);
                this.cardMap.put(id, c);
            }
        }

    }

    public Card getCard(OID id) {
        return cardMap.get(id);
    }

    public List<Card> getCards(List<OID> ids) {
        List<Card> cards = new ArrayList<>();
        for (OID id : ids) {
            cards.add(MTGGame.data().cardMap.get(id));
        }
        return cards;
    }

    public void setCard(OID id, Card c) {
        cardMap.remove(id);
        cardMap.put(id, c);
    }


    public ZONE getZone(OID id) {
        if (battlefield.contains(id)) return ZONE.BATTLEFIELD;
        if (theStack.contains(id)) return ZONE.STACK;
        if (exile.contains(id)) return ZONE.EXILE;
        if (commandZone.contains(id)) return ZONE.COMMAND;

        for (PlayerData p : playerData)
            if (p.graveyard.contains(id))
                return ZONE.GRAVEYARD;


        for (PlayerData p : playerData)
            if (p.hand.contains(id))
                return ZONE.HAND;


        for (PlayerData p : playerData)
            if (p.library.contains(id))
                return ZONE.LIBRARY;

        return ZONE.NONE;
    }

    public boolean isStackEmpty() {
        return theStack.isEmpty();
    }


    private List<ContinuousEffect> getStaticEffects() {
        List<ContinuousEffect> ret = new ArrayList<>();
        for (OID id : battlefield) {
            Card c = MTGGame.getInstance().gameData.getCard(id);
            for (Ability a : c.abilities) {
                if (a instanceof StaticModifierAbility) {
                    StaticModifierAbility sa = (StaticModifierAbility) a;
                    if (sa.condition.apply(c.controller)) {
                        ContinuousEffect e = new ContinuousEffect(c.controller, sa.mod, c, sa.filter);
                        ret.add(e);
                    }
                }
            }
        }
        return ret;
    }

    public void updateBoardstate() {
        // TODO: copy from OldGameState
        // sort all effects into the first place they get applied
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
            Collections.sort(layer1a_StaticEffects);

            ContinuousEffect e = layer1a_StaticEffects.remove(0);
            layer2_StaticEffects.add(e);
            e.applyToObjects(LAYER.L1a);
        }



        // LAYER 2
        for (ContinuousEffect e : layer2_StaticEffects) {
            e.selectAffectedObjects();
        }
        while (!layer2_StaticEffects.isEmpty()) {
            Collections.sort(layer2_StaticEffects);

            ContinuousEffect e = layer2_StaticEffects.remove(0);
            layer4_StaticEffects.add(e);
            e.applyToObjects(LAYER.L2);
        }


        // LAYER 4
        for (ContinuousEffect e : layer4_StaticEffects) {
            e.selectAffectedObjects();
        }
        while (!layer4_StaticEffects.isEmpty()) {
            Collections.sort(layer4_StaticEffects);

            ContinuousEffect e = layer4_StaticEffects.remove(0);
            layer5_StaticEffects.add(e);
            e.applyToObjects(LAYER.L4);
        }

        //LAYER 5
        for (ContinuousEffect e : layer5_StaticEffects) {
            e.selectAffectedObjects();
        }
        while (!layer5_StaticEffects.isEmpty()) {
            Collections.sort(layer5_StaticEffects);

            ContinuousEffect e = layer5_StaticEffects.remove(0);
            layer6_StaticEffects.add(e);
            e.applyToObjects(LAYER.L5);
        }

        //LAYER 6
        for (ContinuousEffect e : layer6_StaticEffects) {
            e.selectAffectedObjects();
        }
        while (!layer6_StaticEffects.isEmpty()) {
            Collections.sort(layer6_StaticEffects);

            ContinuousEffect e = layer6_StaticEffects.remove(0);
            layer7a_StaticEffects.add(e);
            e.applyToObjects(LAYER.L6);
        }


        //LAYER 7a
        for (ContinuousEffect e : layer7a_StaticEffects) {
            e.selectAffectedObjects();
        }
        while (!layer7a_StaticEffects.isEmpty()) {
            Collections.sort(layer7a_StaticEffects);

            ContinuousEffect e = layer7a_StaticEffects.remove(0);
            layer7b_StaticEffects.add(e);
            e.applyToObjects(LAYER.L7a);
        }
        //LAYER 7b
        for (ContinuousEffect e : layer7b_StaticEffects) {
            e.selectAffectedObjects();
        }
        while (!layer7b_StaticEffects.isEmpty()) {
            Collections.sort(layer7b_StaticEffects);

            ContinuousEffect e = layer7b_StaticEffects.remove(0);
            layer7c_StaticEffects.add(e);
            e.applyToObjects(LAYER.L7b);
        }

        //LAYER 7c
        for (ContinuousEffect e : layer7c_StaticEffects) {
            e.selectAffectedObjects();
        }
        while (!layer7c_StaticEffects.isEmpty()) {
            Collections.sort(layer7c_StaticEffects);

            ContinuousEffect e = layer7c_StaticEffects.remove(0);
            layer7d_StaticEffects.add(e);
            e.applyToObjects(LAYER.L7c);
        }



        //LAYER 7d
        for (ContinuousEffect e : layer7d_StaticEffects) {
            e.selectAffectedObjects();
        }
        while (!layer7d_StaticEffects.isEmpty()) {
            Collections.sort(layer7d_StaticEffects);

            ContinuousEffect e = layer7d_StaticEffects.remove(0);
            e.applyToObjects(LAYER.L7d);
        }
    }

    public void startOfStep(PhaseType phase) {
        // TODO: turn based actions at start of step
        switch (phase) {
            // "At the beginning of" effects
            case Untap:
                // Phasing happens
                // All permanents untap
                // No priority
                break;
            case Upkeep:
                // AP Priority
                break;
            case Draw:
                // The active player draws a card
                // AP Priority
                break;
            case Main1:
                // Increment and resolve sagas
                // AP Priority
                break;
            case CombatStart:
                // AP Priority
                break;
            case CombatAttackers:
                // Attackers are declared by AP
                // AP Priority
                break;
            case CombatBlockers:
                // Blockers are declared
                // Attacking player chooses damage assignment order of attackers
                // Defending player chooses damage assignment order of blockers
                // AP Priority
                break;
            case CombatDamage:
                // Attacking player assigns combat damage
                // Defending player assigns combat damage
                // Damage is dealt (simultaneously)
                // AP Priority
                break;
            case CombatEnd:
                // AP Priority
                break;
            case Main2:
                // AP Priority
                break;
            case End:
                // AP Priority
                break;
            case Cleanup:
                // Players discard to max hand size
                // All marked damage is removed
                // No priority
                break;
        }
    }

    public void stateBasedActions() {
        // TODO: implement state based actions
    }

    public void endOfTurn() {
        // TODO: empty mana pools
        // TODO: end of turn effects end
    }

    public void resolveTopOfStack() {
        if (!this.isStackEmpty()) return;

        OID spell = this.theStack.peek();
        // TODO: resolve it
    }
}
