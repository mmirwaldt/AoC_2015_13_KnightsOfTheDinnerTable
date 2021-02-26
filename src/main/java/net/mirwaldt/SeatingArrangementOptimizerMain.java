package net.mirwaldt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SeatingArrangementOptimizerMain {
    public static void main(String[] args) throws IOException {
        final List<String> lines = Files.readAllLines(Path.of("input.txt"), StandardCharsets.US_ASCII);
        final SeatingArrangementOptimizer seatingArrangementOptimizer = new BruteForceSeatingArrangementOptimizer();
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
        }
        final SeatingArrangement seatingArrangement = seatingArrangementOptimizer.findOptimalSeatingArrangement();

        // [Alice, Bob, Frank, Carol, Eric, David, George, Mallory] : 733
        System.out.println(seatingArrangement.getSeating() + " : " + seatingArrangement.getTotalHappinessUnits());
    }
}
