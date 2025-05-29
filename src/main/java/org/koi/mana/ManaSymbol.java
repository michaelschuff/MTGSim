package org.koi.mana;

import org.koi.util.Color;

public class ManaSymbol implements Comparable<ManaSymbol> {
    public final boolean colorless;
    public final boolean generic;
    public final boolean white;
    public final boolean blue;
    public final boolean black;
    public final boolean red;
    public final boolean green;
    public final boolean phyrexian;
    public final boolean snow;
    public final boolean isReduction;
    public final int variable;
    public final int cmc;
    public final int value;


    ManaSymbol(boolean colorless,
               boolean generic,
               boolean white,
               boolean blue,
               boolean black,
               boolean red,
               boolean green,
               boolean phyrexian,
               boolean snow,
               boolean isReduction,
               int variable,
               int cmc) {
        this.colorless = colorless;
        this.generic = generic;
        this.white = white;
        this.blue = blue;
        this.black = black;
        this.red = red;
        this.green = green;
        this.phyrexian = phyrexian;
        this.snow = snow;
        this.isReduction = isReduction;
        this.variable = variable;
        this.cmc = cmc;
        this.value = this.calcValue();
    }

    public ManaSymbol unaryMinus() {
        return new ManaSymbolBuilder(this).isReduction().build();
    }

    public int cmc() {
        return cmc;
    }

    public boolean isColor(Color c) {
        if (c == Color.GENERIC)
            return generic || colorless;

        if (c == Color.WHITE)
            return white;

        if (c == Color.BLUE)
            return blue;

        if (c == Color.BLACK)
            return black;

        if (c == Color.RED)
            return red;

        if (c == Color.GREEN)
            return green;


        return false;
    }

    public String toString() {
        String s = "";
        if (generic) {
            if (variable > 0) {
                if (variable == 1) s += "X/";
                if (variable == 2) s += "Y/";
                if (variable == 3) s += "Z/";
            } else {
                s += cmc + "/";
            }
        }
        if (colorless) s += "C/";
        if (white) s += "W/";
        if (blue) s += "U/";
        if (black) s += "B/";
        if (red) s += "R/";
        if (green) s += "G/";
        if (colorless) s += "C/";
        if (snow) s += "S/";
        if (phyrexian) s += "P/";


        return s.substring(0, s.length() - 1);
    }

    @Override
    public int compareTo(ManaSymbol o) {
        int v = this.value - o.value;
        if (v == 0) {
            // order doesn't matter, unless symbol is {X}, {Y}, or {Z}
            // return reverse order since represented by negatives
            return -(this.cmc - o.cmc);
        }
        return v;
    }

    // determines order for compareTo
    private int calcValue() {
        int v = 0;
        if (this.colorless)     v += 1;
        if (this.variable == 1) v += 2;
        if (this.variable == 2) v += 4;
        if (this.variable == 3) v += 8;
        if (this.generic)       v += 16;
        if (this.white)         v += 32;
        if (this.blue)          v += 64;
        if (this.black)         v += 128;
        if (this.red)           v += 256;
        if (this.green)         v += 512;
        if (this.phyrexian)     v += 1024;
        if (this.snow)          v += 2048;
        return v;
    }

    public static final ManaSymbol X            = new ManaSymbolBuilder().variable(1).build();
    public static final ManaSymbol Y            = new ManaSymbolBuilder().variable(2).build();
    public static final ManaSymbol Z            = new ManaSymbolBuilder().variable(3).build();
    public static final ManaSymbol ZERO         = new ManaSymbolBuilder(0).build();
    public static final ManaSymbol ONE          = new ManaSymbolBuilder(1).build();
    public static final ManaSymbol TWO          = new ManaSymbolBuilder(2).build();
    public static final ManaSymbol THREE        = new ManaSymbolBuilder(3).build();
    public static final ManaSymbol FOUR         = new ManaSymbolBuilder(4).build();
    public static final ManaSymbol FIVE         = new ManaSymbolBuilder(5).build();
    public static final ManaSymbol SIX          = new ManaSymbolBuilder(6).build();
    public static final ManaSymbol SEVEN        = new ManaSymbolBuilder(7).build();
    public static final ManaSymbol EIGHT        = new ManaSymbolBuilder(8).build();
    public static final ManaSymbol NINE         = new ManaSymbolBuilder(9).build();
    public static final ManaSymbol TEN          = new ManaSymbolBuilder(10).build();
    public static final ManaSymbol ELEVEN       = new ManaSymbolBuilder(11).build();
    public static final ManaSymbol TWELVE       = new ManaSymbolBuilder(12).build();
    public static final ManaSymbol THIRTEEN     = new ManaSymbolBuilder(13).build();
    public static final ManaSymbol FOURTEEN     = new ManaSymbolBuilder(14).build();
    public static final ManaSymbol FIFTEEN      = new ManaSymbolBuilder(15).build();
    public static final ManaSymbol SIXTEEN      = new ManaSymbolBuilder(16).build();
    public static final ManaSymbol TWENTY       = new ManaSymbolBuilder(20).build();
    public static final ManaSymbol WHITE        = new ManaSymbolBuilder().white().build();
    public static final ManaSymbol BLUE         = new ManaSymbolBuilder().blue().build();
    public static final ManaSymbol BLACK        = new ManaSymbolBuilder().black().build();
    public static final ManaSymbol RED          = new ManaSymbolBuilder().red().build();
    public static final ManaSymbol GREEN        = new ManaSymbolBuilder().green().build();
    public static final ManaSymbol SNOW         = new ManaSymbolBuilder().snow().build();
    public static final ManaSymbol COLORLESS    = new ManaSymbolBuilder().colorless().build();

    public static final ManaSymbol TWO_WHITE    = new ManaSymbolBuilder().white().generic(2).build();
    public static final ManaSymbol TWO_BLUE     = new ManaSymbolBuilder().blue().generic(2).build();
    public static final ManaSymbol TWO_BLACK    = new ManaSymbolBuilder().black().generic(2).build();
    public static final ManaSymbol TWO_RED      = new ManaSymbolBuilder().red().generic(2).build();
    public static final ManaSymbol TWO_GREEN    = new ManaSymbolBuilder().green().generic(2).build();

    public static final ManaSymbol THREE_WHITE  = new ManaSymbolBuilder().white().generic(3).build();
    public static final ManaSymbol THREE_BLUE   = new ManaSymbolBuilder().blue().generic(3).build();
    public static final ManaSymbol THREE_BLACK  = new ManaSymbolBuilder().black().generic(3).build();
    public static final ManaSymbol THREE_RED    = new ManaSymbolBuilder().red().generic(3).build();
    public static final ManaSymbol THREE_GREEN  = new ManaSymbolBuilder().green().generic(3).build();


    // Allied color pairs
    public static final ManaSymbol AZORIUS     = new ManaSymbolBuilder().white().blue().build();
    public static final ManaSymbol DIMIR       = new ManaSymbolBuilder().blue().black().build();
    public static final ManaSymbol RAKDOS      = new ManaSymbolBuilder().black().red().build();
    public static final ManaSymbol GRUUL       = new ManaSymbolBuilder().red().green().build();
    public static final ManaSymbol SELESNYA    = new ManaSymbolBuilder().green().white().build();

    // Enemy color pairs
    public static final ManaSymbol ORZHOV      = new ManaSymbolBuilder().white().black().build();
    public static final ManaSymbol IZZET       = new ManaSymbolBuilder().blue().red().build();
    public static final ManaSymbol GOLGARI     = new ManaSymbolBuilder().black().green().build();
    public static final ManaSymbol BOROS       = new ManaSymbolBuilder().red().white().build();
    public static final ManaSymbol SIMIC       = new ManaSymbolBuilder().green().blue().build();

    // Shards
    public static final ManaSymbol BANT        = new ManaSymbolBuilder().white().blue().green().build();
    public static final ManaSymbol ESPER       = new ManaSymbolBuilder().blue().black().white().build();
    public static final ManaSymbol GRIXIS      = new ManaSymbolBuilder().black().red().blue().build();
    public static final ManaSymbol JUND        = new ManaSymbolBuilder().red().green().black().build();
    public static final ManaSymbol NAYA        = new ManaSymbolBuilder().green().white().red().build();

    // Wedges
    public static final ManaSymbol ABZAN       = new ManaSymbolBuilder().white().black().green().build();
    public static final ManaSymbol JESKAI      = new ManaSymbolBuilder().blue().red().white().build();
    public static final ManaSymbol SULTAI      = new ManaSymbolBuilder().black().green().blue().build();
    public static final ManaSymbol MARDU       = new ManaSymbolBuilder().red().white().black().build();
    public static final ManaSymbol TEMUR       = new ManaSymbolBuilder().green().blue().red().build();

    // 4 ManaSymbols
    public static final ManaSymbol CHAOS       = new ManaSymbolBuilder().blue().black().red().green().build();
    public static final ManaSymbol AGGRESSION  = new ManaSymbolBuilder().white().black().red().green().build();
    public static final ManaSymbol ALTRUISM    = new ManaSymbolBuilder().white().blue().red().green().build();
    public static final ManaSymbol GROWTH      = new ManaSymbolBuilder().white().blue().black().green().build();
    public static final ManaSymbol ARTIFICE    = new ManaSymbolBuilder().white().blue().black().red().build();

    // 5 ManaSymbol
    public static final ManaSymbol RAINBOW     = new ManaSymbolBuilder().white().blue().black().red().green().build();
}
