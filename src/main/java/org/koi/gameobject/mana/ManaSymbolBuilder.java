package org.koi.gameobject.mana;

public class ManaSymbolBuilder {
    private boolean colorless = false;
    private boolean generic = false;
    private boolean white = false;
    private boolean blue = false;
    private boolean black = false;
    private boolean red = false;
    private boolean green = false;
    private boolean isReduction = false;
    private boolean phyrexian = false;
    private boolean snow = false;
    private int cmc = 1;
    private int variable = 0;

    public ManaSymbolBuilder() { }
    public ManaSymbolBuilder(ManaSymbol s) {
        this.colorless = s.colorless;
        this.generic = s.generic;
        this.white = s.white;
        this.blue = s.blue;
        this.black = s.black;
        this.red = s.red;
        this.green = s.green;
        this.isReduction = s.isReduction;
        this.phyrexian = s.phyrexian;
        this.snow = s.snow;
        this.cmc = s.cmc;
        this.variable = s.variable;
    }
    public ManaSymbolBuilder(int cmc) {
        this.generic = true;
        this.cmc = cmc;
    }

    public ManaSymbolBuilder white() {
        this.white = true;
        return this;
    }
    public ManaSymbolBuilder blue() {
        this.blue = true;
        return this;
    }
    public ManaSymbolBuilder black() {
        this.black = true;
        return this;
    }
    public ManaSymbolBuilder red() {
        this.red = true;
        return this;
    }
    public ManaSymbolBuilder green() {
        this.green = true;
        return this;
    }
    public ManaSymbolBuilder phyrexian() {
        this.phyrexian = true;
        return this;
    }
    public ManaSymbolBuilder snow() {
        this.snow = true;
        return this;
    }
    public ManaSymbolBuilder colorless() {
        this.colorless = true;
        return this;
    }
    public ManaSymbolBuilder isReduction() {
        this.isReduction = true;
        return this;
    }
    public ManaSymbolBuilder generic(int cmc) {
        this.generic = true;
        this.cmc = cmc;
        return this;
    }
    public ManaSymbolBuilder variable(int variable) {
        this.generic = true;
        this.variable = variable;
        return this;
    }
    public ManaSymbol build() {
        return new ManaSymbol(colorless, generic, white, blue, black, red, green, phyrexian, snow, isReduction, variable, cmc);
    }
}
