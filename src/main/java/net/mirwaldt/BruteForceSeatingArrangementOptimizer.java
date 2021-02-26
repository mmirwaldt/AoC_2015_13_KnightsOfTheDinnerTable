package net.mirwaldt;

import java.util.Collections;

public class BruteForceSeatingArrangementOptimizer implements SeatingArrangementOptimizer {
    @Override
    public void addPreference(String name, String neighbour, int happinessUnits) {

    }

    @Override
    public SeatingArrangement findOptimalSeatingArrangement() {
        return new SeatingArrangement(Collections.emptyList(), 0);
    }
}
