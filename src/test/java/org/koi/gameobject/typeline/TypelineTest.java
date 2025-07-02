package org.koi.gameobject.typeline;

import org.junit.Test;

import static org.junit.Assert.*;

public class TypelineTest {

    @Test
    public void isType() {
        Typeline t = new TypelineBuilder().legendary().mountain().advisor().build();
        assert t.isType("Legendary");
        assert t.isType("Mountain");
        assert t.isType("Advisor");

        // our typeline does not work this way, even though in MTG its illegal
        assert !t.isType("Land");
        assert !t.isType("Creature");
    }

    @Test
    public void isPermanent() {
        Typeline t1 = new TypelineBuilder().creature().build();
        Typeline t2 = new TypelineBuilder().planeswalker().build();
        Typeline t3 = new TypelineBuilder().land().build();
        Typeline t4 = new TypelineBuilder().sorcery().build();
        Typeline t5 = new TypelineBuilder().instant().build();
        Typeline t6 = new TypelineBuilder().artifact().build();
        Typeline t7 = new TypelineBuilder().battle().build();

        assert t1.isPermanent();
        assert t2.isPermanent();
        assert t3.isPermanent();
        assert !t4.isPermanent();
        assert !t5.isPermanent();
        assert t6.isPermanent();
        assert t7.isPermanent();
    }

    @Test
    public void and() {
        Typeline t1 = new TypelineBuilder().legendary().mountain().advisor().build();
        Typeline t2 = new TypelineBuilder().basic().mountain().build();
        Typeline result = t1.and(t2);
        assert !result.isType("Advisor");
        assert result.isType("Mountain");
        assert !result.isType("Legendary");
        assert !result.isType("Basic");
    }

    @Test
    public void minus() {
        Typeline t1 = new TypelineBuilder().legendary().mountain().advisor().build();
        Typeline t2 = new TypelineBuilder().basic().mountain().build();
        Typeline result = t1.minus(t2);

        assert result.isType("Legendary");
        assert !result.isType("Mountain");
        assert result.isType("Advisor");
        assert !result.isType("Basic");
    }

    @Test
    public void or() {
        Typeline t1 = new TypelineBuilder().legendary().mountain().advisor().build();
        Typeline t2 = new TypelineBuilder().basic().mountain().build();
        Typeline result = t1.or(t2);

        assert result.isType("Legendary");
        assert result.isType("Mountain");
        assert result.isType("Advisor");
        assert result.isType("Basic");
    }
}