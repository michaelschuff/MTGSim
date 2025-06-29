package org.koi.cards;

import org.koi.game.MTGGame;
import org.koi.gameobject.card.OracleCard;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CardLoader {
    public static OracleCard loadCard(MTGGame game, String name) {
        try {
            Method m = Class.forName("org.koi.cards." + name).getMethod("getCard", MTGGame.class);
            return (OracleCard) m.invoke(null, game);
        } catch (ClassNotFoundException e) {
            System.out.println("Card does not exist");
        } catch (NoSuchMethodException e) {
            System.out.println(name + " class missing getCard method");
        } catch (InvocationTargetException e) {
            System.out.println(e);
        } catch (IllegalAccessException e) {
            System.out.println(e);
        }
        return null;
    }
}
