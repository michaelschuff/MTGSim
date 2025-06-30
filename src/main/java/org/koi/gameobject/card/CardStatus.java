package org.koi.gameobject.card;

import org.koi.util.GameObjectOrPlayer;

import java.util.List;

public class CardStatus {
    public boolean tapped       = false;
    public boolean phasedIn     = true;
    public boolean faceUp       = true;
    public boolean flipped      = false;
    public boolean transformed  = false;
    public boolean monstrous    = false;
    public boolean attacking    = false;
    public boolean blocking     = false;

    public boolean recievedDeathtouchDamageFlag = false;

    public int                  damage              = 0;
    public List<Card>           inCombatWith        = List.of();
    public List<Integer>        damageAssignment    = List.of();
    public GameObjectOrPlayer   attackTarget        = null;

    public CardStatus() { }

    public String toString() {
        String s = "Status:";
        s += "\n\t     tapped: " + tapped;
        s += "\n\t   phasedIn: " + phasedIn;
        s += "\n\t     faceUp: " + faceUp;
        s += "\n\t    flipped: " + flipped;
        s += "\n\ttransformed: " + transformed;
        s += "\n\t     damage: " + damage;
        return s;
    }

    public boolean getStatus(CARD_STATUS_TYPE type) {
        switch (type) {
            case TAPPED:
                return tapped;
            case PHASED_IN:
                return phasedIn;
            case FACE_UP:
                return faceUp;
            case FLIPPED:
                return flipped;
            case TRANSFORMED:
                return transformed;
            case MONSTROUS:
                return monstrous;
            case ATTACKING:
                return attacking;
            case BLOCKING:
                return blocking;
            default:
                return false;
        }
    }
}
