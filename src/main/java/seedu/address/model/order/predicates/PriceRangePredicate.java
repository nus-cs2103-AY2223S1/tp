package seedu.address.model.order.predicates;

import java.util.function.Predicate;

import seedu.address.model.order.Order;
import seedu.address.model.order.Price;
import seedu.address.model.order.PriceRange;

/**
 * Tests that a {@code Order}'s {@code PriceRange} matches any of the keywords given.
 */
public class PriceRangePredicate<T extends Order> implements Predicate<T> {
    private final Price lowerBound;
    private final Price upperBound;

    /**
     * Constructs a PriceRangePredicate.
     *
     * @param lowerBound The lower bound Price.
     * @param upperBound The upper bound Price.
     */
    public PriceRangePredicate(Price lowerBound, Price upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public boolean test(T order) {
        Price orderLowerBound = order.getRequestedPriceRange().getLowerBound();
        Price orderUpperBound = order.getRequestedPriceRange().getUpperBound();
        PriceRange thisRange = new PriceRange(lowerBound, upperBound);
        boolean isThisLowerNA = lowerBound.isNotApplicablePrice();
        boolean isThisUpperNA = upperBound.isNotApplicablePrice();
        boolean isOtherLowerNA = orderLowerBound.isNotApplicablePrice();
        boolean isOtherUpperNA = orderUpperBound.isNotApplicablePrice();
        boolean isAboveLowerBound = thisRange.comparePrice(orderLowerBound) == PriceRange.WITHIN_RANGE
                && ((!isOtherLowerNA) || (isThisLowerNA) || (lowerBound.getPrice() == 0));
        boolean isBelowUpperBound = thisRange.comparePrice(orderUpperBound) == PriceRange.WITHIN_RANGE
                && ((!isOtherUpperNA) || (isThisUpperNA));
        return isAboveLowerBound && isBelowUpperBound;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriceRangePredicate // instanceof handles nulls
                && lowerBound.equals(((PriceRangePredicate) other).lowerBound)
                && upperBound.equals(((PriceRangePredicate) other).upperBound)); // state check
    }
}
