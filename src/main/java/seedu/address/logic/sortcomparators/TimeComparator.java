package seedu.address.logic.sortcomparators;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * A comparator to compare two LocalDateTimes.
 */
public class TimeComparator implements Comparator<LocalDateTime> {

    private final Order order;

    /**
     * Constructs a {@code TimeComparator}.
     *
     * @param order The specified order of comparison.
     */
    public TimeComparator(Order order) {
        requireNonNull(order);
        this.order = order;
    }

    @Override
    public int compare(LocalDateTime firstTime, LocalDateTime secondTime) {
        int comparisonValue = firstTime.compareTo(secondTime);
        return order.equals(new Order("ASC")) ? comparisonValue : -comparisonValue;
    }

    @Override
    public String toString() {
        return "Entry Time, " + order;
    }
}
