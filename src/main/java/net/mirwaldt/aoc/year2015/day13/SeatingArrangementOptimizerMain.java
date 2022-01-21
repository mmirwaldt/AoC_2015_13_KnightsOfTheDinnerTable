package net.mirwaldt.aoc.year2015.day13;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class SeatingArrangementOptimizerMain {
    public static void main(String[] args) throws IOException {
        final List<String> lines = Files.readAllLines(Path.of("input.txt"), StandardCharsets.US_ASCII);

        final SeatingArrangementOptimizer seatingArrangementOptimizerForPartOne =
                new BruteForceSeatingArrangementOptimizer();
        addPreferences(lines, seatingArrangementOptimizerForPartOne);
        final SeatingArrangement seatingArrangementOfPartOne =
                seatingArrangementOptimizerForPartOne.findOptimalSeatingArrangement();

        // [Alice, Bob, Frank, Carol, Eric, David, George, Mallory] : 733
        System.out.println(seatingArrangementOfPartOne.getSeating() + " : "
                + seatingArrangementOfPartOne.getTotalHappinessUnits());



        final SeatingArrangementOptimizer seatingArrangementOptimizerForPartTwo =
                new BruteForceSeatingArrangementOptimizer();
        final SortedSet<String> allNames = addPreferences(lines, seatingArrangementOptimizerForPartTwo);
        final String myName = "Michael";
        for (String name : allNames) {
            seatingArrangementOptimizerForPartTwo.addPreference(myName, name, 0);
            seatingArrangementOptimizerForPartTwo.addPreference(name, myName, 0);
        }

        final SeatingArrangement seatingArrangementOfPartTwo =
                seatingArrangementOptimizerForPartTwo.findOptimalSeatingArrangement();

        // [Alice, Bob, Frank, Carol, Eric, David, George, Michael, Mallory] : 725
        System.out.println(seatingArrangementOfPartTwo.getSeating() + " : "
                + seatingArrangementOfPartTwo.getTotalHappinessUnits());
    }

    private static SortedSet<String> addPreferences(
            List<String> lines, SeatingArrangementOptimizer seatingArrangementOptimizer) {
        final SortedSet<String> allNames = new TreeSet<>();
        for (String line : lines) {
            final String[] tokens =  line.split(" ");
            final String name = tokens[0];
            final String neighbour = tokens[10].replace(".", "");
            final int happinessUnits;
            if("lose".equals(tokens[2])) {
                happinessUnits = -Integer.parseInt(tokens[3]);
            } else {
                happinessUnits = Integer.parseInt(tokens[3]);
            }
            seatingArrangementOptimizer.addPreference(name, neighbour, happinessUnits);
            allNames.add(name);
            allNames.add(neighbour);
        }
        return allNames;
    }
}
