package seedu.address.logic.sortcomparators;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.sortcomparators.Order.OrderType.ASC;

import java.util.Comparator;

import seedu.address.model.pricerange.PriceRange;


/**
 * A comparator to compare two PriceRanges.
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
        return "Budget, " + order
                + String.format(" (based on %s of price range)",
                    order.equals(new Order("asc")) ? "start" : "end");
    }
}
