package seedu.address.logic.sortcomparators;

import seedu.address.model.pricerange.PriceRange;

import java.util.Comparator;

import static java.util.Objects.requireNonNull;

/**
 * A comparator to compare two Names.
 */
public class PriceRangeComparator implements Comparator<PriceRange> {

    private final Order order;

    /**
     * Constructs a {@code PriceRangeComparator}.
     *
     * @param order The specified order of comparison.
     */
    public PriceRangeComparator(Order order) {
        requireNonNull(order);
        this.order = order;
    }

    @Override
    public int compare(PriceRange firstPriceRange, PriceRange secondPriceRange) {
        return order.equals(new Order("ASC"))
                ? firstPriceRange.compareLowerBound(secondPriceRange)
                : firstPriceRange.compareUpperBound(secondPriceRange);
    }

    @Override
    public String toString() {
        return "Sorted by pricerange "
                + (order.equals(new Order("ASC")) ? "lowerbound" : "upperbound");
    }
}
