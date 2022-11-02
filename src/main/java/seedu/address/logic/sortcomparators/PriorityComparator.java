package seedu.address.logic.sortcomparators;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.model.buyer.Priority;


/**
 * A comparator to compare two Priorities.
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
        return "Priority, " + order;
    }
}
