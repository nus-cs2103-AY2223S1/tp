package seedu.condonery.model.property;

import java.util.function.Predicate;

/**
 * Tests that a {@code Property}'s {@code Price} is within specified range.
 */
public class PropertyPriceWithinRangePredicate implements Predicate<Property> {
    private final Integer lower;
    private final Integer upper;

    /**
     * Constructs predicate with lower bound and upper bound for price.
     *
     * @param lower lower bound of price range (inclusive)
     * @param upper upper bound of price range (inclusive)
     */
    public PropertyPriceWithinRangePredicate(int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
    }

    @Override
    public boolean test(Property property) {
        return property.getPrice().amount >= lower && property.getPrice().amount <= upper;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyPriceWithinRangePredicate // instanceof handles nulls
                && lower.equals(((PropertyPriceWithinRangePredicate) other).lower))
                && upper.equals(((PropertyPriceWithinRangePredicate) other).upper); // state check
    }

}
