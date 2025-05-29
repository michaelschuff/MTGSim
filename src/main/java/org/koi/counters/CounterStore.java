package org.koi.counters;

import java.util.HashMap;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class CounterStore {
    private final HashMap<Counter, Integer> data = new HashMap<>();

    public void inc(Counter counter, int amount = 1) {
        data.merge(counter, amount, Integer::sum);
    }

    public void dec(Counter counter, int amount = 1) {
        if (data.containsKey(counter)) {
            data.put(counter, data.get(counter) - amount);
        }
    }

    public int remove(Counter counter) {
        return data.remove(counter);
    }

    public void clear() {
        data.clear();
    }

    // returns number of counters removed
    public int annihilate_p1p1_m1m1() {
        int p1p1 = data.getOrDefault(Counter.p1p1, 0);
        int m1m1 = data.getOrDefault(Counter.m1m1, 0);
        if (p1p1 < m1m1) {
            data.remove(Counter.p1p1);
            data.merge(Counter.m1m1, -p1p1, Integer::sum);
        } else if (m1m1 < p1p1) {
            data.remove(Counter.m1m1);
            data.merge(Counter.p1p1, -m1m1, Integer::sum);
        } else { // if (p1p1 == m1m1)
            data.remove(Counter.m1m1);
            data.remove(Counter.p1p1);
        }
        return abs(m1m1-p1p1);
    }
}
