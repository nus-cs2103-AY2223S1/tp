package seedu.address.model.order.predicates;

import java.util.function.Predicate;

import seedu.address.model.order.Order;
import seedu.address.model.order.Price;

public class PriceRangePredicate<T extends Order> implements Predicate<T> {
    private final Price lowerBound;
    private final Price upperBound;

    public PriceRangePredicate(Price lowerBound, Price upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public boolean test(T order) {
        Price orderLowerBound = order.getRequestedPriceRange().getLowerBound();
        Price orderUpperBound = order.getRequestedPriceRange().getUpperBound();
        boolean isAboveLowerBound = orderLowerBound.getPrice() >= lowerBound.getPrice();
        boolean isBelowUpperBound = orderUpperBound.getPrice() <= upperBound.getPrice();
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
