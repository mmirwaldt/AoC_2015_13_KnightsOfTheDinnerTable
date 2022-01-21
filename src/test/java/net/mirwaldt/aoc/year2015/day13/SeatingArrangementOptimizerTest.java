package net.mirwaldt.aoc.year2015.day13;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeatingArrangementOptimizerTest {
    private static Stream<Arguments> seatingArrangementOptimizer() {
        return Stream.of(Arguments.of(new BruteForceSeatingArrangementOptimizer()));
    }

    @ParameterizedTest
    @MethodSource("seatingArrangementOptimizer")
    void test_findOptimalSeatingArrangement(SeatingArrangementOptimizer seatingArrangementOptimizer) {
        addPreferences(seatingArrangementOptimizer);
        final SeatingArrangement seatingArrangement = seatingArrangementOptimizer.findOptimalSeatingArrangement();
        assertEquals(Arrays.asList("Alice", "Bob", "Carol", "David"), seatingArrangement.getSeating());
        assertEquals(330, seatingArrangement.getTotalHappinessUnits());
    }

    @Test
    void test_calculateHappiness() {
        BruteForceSeatingArrangementOptimizer seatingArrangementOptimizer =
                new BruteForceSeatingArrangementOptimizer();
        addPreferences(seatingArrangementOptimizer);
        assertEquals(330, seatingArrangementOptimizer.calculateHappiness(
                Arrays.asList("Alice", "Bob", "Carol", "David")));
    }

    private void addPreferences(SeatingArrangementOptimizer seatingArrangementOptimizer) {
    /*
        Alice would gain 54 happiness units by sitting next to Bob.
        Alice would lose 79 happiness units by sitting next to Carol.
        Alice would lose 2 happiness units by sitting next to David.
        Bob would gain 83 happiness units by sitting next to Alice.
        Bob would lose 7 happiness units by sitting next to Carol.
        Bob would lose 63 happiness units by sitting next to David.
        Carol would lose 62 happiness units by sitting next to Alice.
        Carol would gain 60 happiness units by sitting next to Bob.
        Carol would gain 55 happiness units by sitting next to David.
        David would gain 46 happiness units by sitting next to Alice.
        David would lose 7 happiness units by sitting next to Bob.
        David would gain 41 happiness units by sitting next to Carol.
     */
        seatingArrangementOptimizer.addPreference("Alice", "Bob", 54);
        seatingArrangementOptimizer.addPreference("Alice", "Carol", -79);
        seatingArrangementOptimizer.addPreference("Alice", "David", -2);
        seatingArrangementOptimizer.addPreference("Bob", "Alice", 83);
        seatingArrangementOptimizer.addPreference("Bob", "Carol", -7);
        seatingArrangementOptimizer.addPreference("Bob", "David", -63);
        seatingArrangementOptimizer.addPreference("Carol", "Alice", -62);
        seatingArrangementOptimizer.addPreference("Carol", "Bob", 60);
        seatingArrangementOptimizer.addPreference("Carol", "David", 55);
        seatingArrangementOptimizer.addPreference("David", "Alice", 46);
        seatingArrangementOptimizer.addPreference("David", "Bob", -7);
        seatingArrangementOptimizer.addPreference("David", "Carol", 41);
    }

}
