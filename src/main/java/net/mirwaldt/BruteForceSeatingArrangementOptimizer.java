package net.mirwaldt;

import java.util.*;
import java.util.stream.Collectors;

public class BruteForceSeatingArrangementOptimizer implements SeatingArrangementOptimizer {
    private final Map<List<String>, Integer> preferences = new HashMap<>();

    @Override
    public void addPreference(String name, String neighbour, int happinessUnits) {
        preferences.put(Arrays.asList(name, neighbour), happinessUnits);
    }

    @Override
    public SeatingArrangement findOptimalSeatingArrangement() {
        final SortedSet<String> allNames = preferences.keySet().stream().
                flatMap(Collection::stream).collect(Collectors.toCollection(TreeSet::new));
        if (2 <= allNames.size()) {
            final Map<List<String>, Integer> seatingByHappiness = seat(allNames, Collections.emptyList());
            Map.Entry<List<String>, Integer> seatingWithMaxHappiness =
                    seatingByHappiness.entrySet().stream()
                            .max(Comparator.comparingInt(Map.Entry::getValue)).get();

            return new SeatingArrangement(seatingWithMaxHappiness.getKey(), seatingWithMaxHappiness.getValue());
        } else {
            throw new IllegalStateException("At least two persons are needed for a seating!");
        }
    }

    private Map<List<String>, Integer> seat(SortedSet<String> names, List<String> seating) {
        if (names.isEmpty()) {
            return Map.of(seating, calculateHappiness(seating));
        } else {
            final Map<List<String>, Integer> seatingsByHappiness = new LinkedHashMap<>();
            for (String name : names) {
                SortedSet<String> restNames = new TreeSet<>(names);
                restNames.remove(name);
                List<String> newSeating = new ArrayList<>(seating);
                newSeating.add(name);
                seatingsByHappiness.putAll(seat(restNames, newSeating));
            }
            return seatingsByHappiness;
        }
    }

    int calculateHappiness(List<String> seating) {
        int totalHappiness = 0;
        for (int i = 0; i < seating.size(); i++) {
            int fromIndex = i % seating.size();
            int toIndex = (i + 1) % seating.size();
            final List<String> seatingPair = new ArrayList<>(); // we cannot use sublist() because we use reverse() later!
            seatingPair.add(seating.get(fromIndex));
            seatingPair.add(seating.get(toIndex));

            Integer happinessForLeft = preferences.get(seatingPair);
            if (happinessForLeft == null) {
                throw new IllegalStateException("Cannot find happiness for seating pair " + seatingPair);
            }
            totalHappiness += happinessForLeft;

            Collections.reverse(seatingPair);
            Integer happinessForRight = preferences.get(seatingPair);
            if (happinessForRight == null) {
                throw new IllegalStateException("Cannot find happiness for seating pair " + seatingPair);
            }
            totalHappiness += happinessForRight;
        }
        return totalHappiness;
    }
}
