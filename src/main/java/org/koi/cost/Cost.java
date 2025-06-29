package org.koi.cost;

import java.util.ArrayList;
import java.util.List;

public class Cost extends ArrayList<Payable> {
    public Cost(List<Payable> costs) {
        this.addAll(costs);
    }
}
