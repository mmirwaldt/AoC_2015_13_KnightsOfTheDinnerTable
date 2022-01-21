package net.mirwaldt.aoc.year2015.day13;

public interface SeatingArrangementOptimizer {
    void addPreference(String name, String neighbour, int happinessUnits);
    SeatingArrangement findOptimalSeatingArrangement();
}
