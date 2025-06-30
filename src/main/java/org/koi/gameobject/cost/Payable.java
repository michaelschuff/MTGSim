package org.koi.gameobject.cost;

import org.koi.game.MTGGame;
import org.koi.util.Player;

public interface Payable {
    boolean canPay(Player player);
    void pay(Player player);
}
