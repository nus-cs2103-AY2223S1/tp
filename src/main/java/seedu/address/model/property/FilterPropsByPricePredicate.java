package seedu.address.model.property;

import static java.util.Objects.requireNonNull;

import seedu.address.model.price.PriceRange;



/**
 * Tests that a {@code Property}'s {@code Price} is within the given price range.
 */
public class FilterPropsByPricePredicate extends AbstractFilterPropsPredicate {

    private final PriceRange priceRange;

    /**
     * Standard constructor for the predicate.
     */
    public FilterPropsByPricePredicate(PriceRange priceRange) {
        requireNonNull(priceRange);
        this.priceRange = priceRange;
    }

    @Override
    public boolean test(Property p) {
        return priceRange.isWithinPriceRange(p.getPrice());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterPropsByPricePredicate // instanceof handles nulls
                && priceRange.equals(((FilterPropsByPricePredicate) other).priceRange)); // state check
    }

}
