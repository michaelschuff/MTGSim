package org.koi.gameobject;

public class PowTou {
    private int power;
    private int toughness;

    public PowTou(int p, int t) {
        this.power = p;
        this.toughness = t;
    }

    public PowTou(PowTou o) {
        this.power = o.power;
        this.toughness = o.toughness;
    }

    public PowTou plus(PowTou o) {
        return new PowTou(this.power + o.power, this.toughness + o.toughness);
    }
    public PowTou minus(PowTou o) {
        return new PowTou(this.power - o.power, this.toughness - o.toughness);
    }
    public PowTou m1m1() {
        return new PowTou(this.power - 1, this.toughness - 1);
    }
    public PowTou p1p1() {
        return new PowTou(this.power + 1, this.toughness + 1);
    }



    public void swap() {
        int temp = power;
        this.power = toughness;
        this.toughness = temp;
    }

    public int power() {
        return power;
    }
    public int toughness() {
        return toughness;
    }

    @Override
    public String toString() {
        return power + "/" + toughness;
    }
}
