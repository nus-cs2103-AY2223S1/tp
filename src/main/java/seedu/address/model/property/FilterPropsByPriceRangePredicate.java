package seedu.address.model.property;

import static java.util.Objects.requireNonNull;

import seedu.address.model.pricerange.PriceRange;



/**
 * Tests that a {@code Property}'s {@code Price} is within the given price range.
 */
public class FilterPropsByPriceRangePredicate extends AbstractFilterPropsPredicate {

    private final PriceRange priceRange;

    /**
     * Standard constructor for the predicate.
     */
    public FilterPropsByPriceRangePredicate(PriceRange priceRange) {
        requireNonNull(priceRange);
        this.priceRange = priceRange;
    }

    @Override
    public boolean test(Property p) {
        return priceRange != null && priceRange.isWithinPriceRange(p.getPrice());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterPropsByPriceRangePredicate // instanceof handles nulls
                && priceRange.equals(((FilterPropsByPriceRangePredicate) other).priceRange)); // state check
    }

}
