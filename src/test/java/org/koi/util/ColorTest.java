package org.koi.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ColorTest {

    @Test
    public void or() {
        Color expected1 = Color.DIMIR;
        Color actual1 = Color.BLUE | Color.BLACK;

        assert expected1.equals(actual1);


        Color expected2 = Color.SELESNYA;
        Color actual2 = Color.SELESNYA | Color.WHITE;

        assert expected2.equals(actual2);
    }

    @Test
    public void and() {
        Color expected1 = Color.COLORLESS;
        Color actual1 = Color.BLUE & Color.BLACK;

        assert expected1.equals(actual1);


        Color expected2 = Color.BLUE;
        Color actual2 = Color.DIMIR | Color.BLACK;

        assert expected2.equals(actual2);
    }

    @Test
    public void minus() {
        Color expected1 = Color.BLUE;
        Color actual1 = Color.DIMIR - Color.BLACK;

        assert expected1.equals(actual1);


        Color expected2 = Color.DIMIR;
        Color actual2 = Color.RAINBOW - Color.ABZAN;

        assert expected2.equals(actual2);
    }

    @Test
    public void inv() {
        Color expected1 = Color.ABZAN;
        Color actual1 = ~Color.DIMIR;

        assert expected1.equals(actual1);


        Color expected2 = Color.RAINBOW;
        Color actual2 = ~Color.COLORLESS;

        assert expected2.equals(actual2);
    }

    @Test
    public void matches() {
        // Is Abzan White and Red?
        Color color1 = Color.ABZAN;
        Color filter1 = Color.BOROS;

        assert color1.matches(filter1);


        Color color2 = Color.RED;
        Color filter2 = ~Color.RAKDOS;

        assert color2.matches(filter2);
    }

    @Test
    public void testEquals() {
        Color c1 = Color.ABZAN;
        Color c2 = Color.RED | Color.SELESNYA;

        assert c1.equals(c2);


        Color c3 = Color.COLORLESS;
        Color c4 = ~Color.RAINBOW;

        assert c3.equals(c4);
    }
}