package net.mirwaldt;

public interface SeatingArrangementOptimizer {
    void addPreference(String name, String neighbour, int happinessUnits);
    SeatingArrangement findOptimalSeatingArrangement();
}
