package seedu.address.logic.sortcomparators;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.Optional;

import seedu.address.model.price.PriceRange;


/**
 * A comparator to compare two PriceRanges.
 */
public class PriceRangeComparator implements Comparator<Optional<PriceRange>> {

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
    public int compare(Optional<PriceRange> firstPriceRange, Optional<PriceRange> secondPriceRange) {
        return firstPriceRange.isEmpty() && secondPriceRange.isEmpty()
                ? 0
                : firstPriceRange.isEmpty()
                ? 1
                : secondPriceRange.isEmpty()
                ? -1
                : order.equals(new Order("ASC"))
                ? firstPriceRange.get().compareLowerBound(secondPriceRange.get())
                : firstPriceRange.get().compareUpperBound(secondPriceRange.get());
    }

    @Override
    public String toString() {
        return "Price Range, " + order
                + String.format(" (based on %s bound of price range)",
                    order.equals(new Order("asc")) ? "lower" : "upper");
    }
}
