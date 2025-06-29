package org.koi.cost;

import java.util.ArrayList;
import java.util.List;

public class Cost extends ArrayList<CostEvent> {
    public Cost(List<CostEvent> costs) {
        this.addAll(costs);
    }
}
