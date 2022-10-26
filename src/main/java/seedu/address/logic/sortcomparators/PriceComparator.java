package seedu.address.logic.sortcomparators;

import seedu.address.model.pricerange.PriceRange;
import seedu.address.model.property.Price;

import java.util.Comparator;

import static java.util.Objects.requireNonNull;

/**
 * A comparator to compare two Names.
 */
public class PriceComparator implements Comparator<Price> {

    private final Order order;

    /**
     * Constructs a {@code PriceComparator}.
     *
     * @param order The specified order of comparison.
     */
    public PriceComparator(Order order) {
        requireNonNull(order);
        this.order = order;
    }

    @Override
    public int compare(Price firstPrice, Price secondPrice) {
        int comparisonValue = firstPrice.isGreaterThanOrEqual(secondPrice) ? 1 : -1;
        return order.equals(new Order("ASC")) ? comparisonValue : -comparisonValue;
    }

    @Override
    public String toString() {
        return "Sorted by price in " + order;
    }
}
