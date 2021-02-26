package net.mirwaldt;

import java.util.List;
import java.util.Objects;

public class SeatingArrangement {
    private final List<String> seating;
    private final int totalHappinessUnits;

    public SeatingArrangement(List<String> seating, int totalHappinessUnits) {
        this.seating = seating;
        this.totalHappinessUnits = totalHappinessUnits;
    }

    public List<String> getSeating() {
        return seating;
    }

    public int getTotalHappinessUnits() {
        return totalHappinessUnits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatingArrangement that = (SeatingArrangement) o;
        return totalHappinessUnits == that.totalHappinessUnits && Objects.equals(seating, that.seating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seating, totalHappinessUnits);
    }

    @Override
    public String toString() {
        return "SeatingArrangement{" +
                "seating=" + seating +
                ", totalHappinessUnits=" + totalHappinessUnits +
                '}';
    }
}
