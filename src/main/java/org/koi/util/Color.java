package org.koi.util;

public class Color {


    private final int value;

    private Color(int val) {
        this.value = val;
    }

    public Color or(Color o) {
        return new Color(this.value | o.value);
    }
    public Color and(Color o) {
        return new Color(this.value & o.value);
    }
    public Color minus(Color o) {
        return new Color(this.value ^ (this.value & o.value));
    }
    public Color inv() {
        return new Color(62 ^ (62 & this.value));
    }

    public int getValue() {
        return value;
    }
    public boolean matches(Color o) {
        return (this.value & o.value) == o.value;
    }

    @Override
    public String toString() {
        String str = "";
        if ((this.value & 2) > 0) {
            str += "W/";
        }
        if ((this.value & 4) > 0) {
            str += "U/";
        }
        if ((this.value & 8) > 0) {
            str += "B/";
        }
        if ((this.value & 16) > 0) {
            str += "R/";
        }
        if ((this.value & 32) > 0) {
            str += "G/";
        }
        if (str.isEmpty()) {
            return "Colorless";
        }

        return str.substring(0, str.length()-1);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color c = (Color) o;
        return value == c.value;
    }

    public static final Color COLORLESS = new Color(0);
    public static final Color NONE = COLORLESS;
    public static final Color GENERIC = new Color(1);
    public static final Color WHITE = new Color(2);
    public static final Color BLUE = new Color(4);
    public static final Color BLACK = new Color(8);
    public static final Color RED = new Color(16);
    public static final Color GREEN = new Color(32);

    // Allied colour pairs
    public static final Color AZORIUS = WHITE | BLUE;
    public static final Color DIMIR = BLUE | BLACK;
    public static final Color RAKDOS = BLACK | RED;
    public static final Color GRUUL = RED | GREEN;
    public static final Color SELESNYA = GREEN | WHITE;

    // Enemy colour pairs
    public static final Color ORZHOV = WHITE | BLACK;
    public static final Color IZZET = BLUE | RED;
    public static final Color GOLGARI = BLACK | GREEN;
    public static final Color BOROS = RED | WHITE;
    public static final Color SIMIC = GREEN | BLUE;

    // Shards
    public static final Color BANT = WHITE | BLUE | GREEN;
    public static final Color ESPER = BLUE | BLACK | WHITE;
    public static final Color GRIXIS = BLACK | RED | BLUE;
    public static final Color JUND = RED | GREEN | BLACK;
    public static final Color NAYA = GREEN | WHITE | RED;

    // Wedges
    public static final Color ABZAN = WHITE | BLACK | GREEN;
    public static final Color JESKAI = BLUE | RED | WHITE;
    public static final Color SULTAI = BLACK | GREEN | BLUE;
    public static final Color MARDU = RED | WHITE | BLACK;
    public static final Color TEMUR = GREEN | BLUE | RED;

    // 4 colors
    public static final Color CHAOS = WHITE.inv();
    public static final Color AGGRESSION = BLUE.inv();
    public static final Color ALTRUISM = BLACK.inv();
    public static final Color GROWTH = RED.inv();
    public static final Color ARTIFICE = GREEN.inv();

    // 5 color
    public static final Color RAINBOW = WHITE | BLUE | BLACK | RED | GREEN;

}
