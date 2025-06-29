package org.koi.zone;


import org.koi.gameobject.GameObject;

public class MTGStack extends AbstractZone<GameObject> {

    @Override
    public ZONE getType() {
        return ZONE.STACK;
    }

}
