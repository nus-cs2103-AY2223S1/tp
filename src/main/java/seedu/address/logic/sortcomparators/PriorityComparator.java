package seedu.address.logic.sortcomparators;

import seedu.address.model.buyer.Priority;


import java.util.Comparator;

import static java.util.Objects.requireNonNull;

/**
 * A comparator to compare two Names.
 */
public class PriorityComparator implements Comparator<Priority> {

    private final Order order;

    /**
     * Constructs a {@code PriorityComparator}.
     *
     * @param order The specified order of comparison.
     */
    public PriorityComparator(Order order) {
        requireNonNull(order);
        this.order = order;
    }

    @Override
    public int compare(Priority firstPriority, Priority secondPriority) {
        int comparisonValue = firstPriority.compareTo(secondPriority);
        return order.equals(new Order("ASC")) ? -comparisonValue : comparisonValue;
    }

    @Override
    public String toString() {
        return "Sorted by priority in " + order;
    }
}
