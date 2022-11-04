package seedu.address.logic.sortcomparators;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.model.property.Price;

/**
 * A comparator to compare two Prices.
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
        return "Price, " + order;
    }
}
