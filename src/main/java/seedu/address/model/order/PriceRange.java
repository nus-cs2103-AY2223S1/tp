package seedu.address.model.order;

import java.util.Objects;

/**
 * Represents the price range (lower bound, upper bound) of an order during negotiation.
 */
public class PriceRange implements Comparable<PriceRange> {

    public static final String DELIMITER = ",";
    public static final String MESSAGE_USAGE =
            "The price range must be two non-negative decimal numbers, separated by a comma. "
            + "For example, 0.8,2.1. The left should be smaller."
            + "If you have not decided one bound, you can enter " + Price.NOT_APPLICABLE_PRICE
            + " to indicate non-applicable price. "
            + "For example, 0.7,-1 means the price is at least 0.7 but not bounded above. ";
    public static final String MESSAGE_CONSTRAINT =
            "The settled price must be within the range! For example, -1 is within any range; 5 is within (-1, 6);"
                    + "7 is within (2, -1)";
    public static final int LOWER_THAN_RANGE = -1;
    public static final int WITHIN_RANGE = 0;
    public static final int HIGHER_THAN_RANGE = 1;

    private Price upperBound;
    private Price lowerBound;

    /**
     * Constructs a PriceRange object.
     *
     * @param lowerBound The bound that the final price is expected not to be smaller than.
     * @param upperBound The bound that the final price is expected not to be greater than
     */
    public PriceRange(Price lowerBound, Price upperBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }

    public Price getUpperBound() {
        return upperBound;
    }

    public Price getLowerBound() {
        return lowerBound;
    }

    public void setUpperBound(Price upperBound) {
        this.upperBound = upperBound;
    }

    public void setLowerBound(Price lowerBound) {
        this.lowerBound = lowerBound;
    }

    /**
     * Updates both bounds.
     *
     * @param lowerBound The bound that the final price is expected not to be smaller than.
     * @param upperBound The bound that the final price is expected not to be greater than
     */
    public void updatePriceRange(Price lowerBound, Price upperBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }


    /**
     * Checks if a price is within the price range.
     *
     * @param price The price to be checked.
     * @return {@code WITHIN_RANGE} if the price passed is within the range,
     *      {@code LOWER_THAN_RANGE}, {@code HIGHER_THAN_RANGE} respectively.
     */
    public int comparePrice(Price price) {
        if (price.isNotApplicablePrice()) {
            return WITHIN_RANGE;
        }
        if (lowerBound.isNotApplicablePrice()) {
            if (upperBound.isNotApplicablePrice()) {
                return WITHIN_RANGE;
            } else {
                return price.compareTo(upperBound) <= 0 ? WITHIN_RANGE : HIGHER_THAN_RANGE;
            }
        } else {
            if (upperBound.isNotApplicablePrice()) {
                return price.compareTo(lowerBound) >= 0 ? WITHIN_RANGE : LOWER_THAN_RANGE;
            } else {
                if (price.compareTo(lowerBound) >= 0) {
                    return price.compareTo(upperBound) <= 0 ? WITHIN_RANGE : HIGHER_THAN_RANGE;
                } else {
                    return LOWER_THAN_RANGE;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Price range: " + lowerBound.getPrice() + " - " + upperBound.getPrice();
    }

    @Override
    public int hashCode() {
        return Objects.hash(upperBound, lowerBound);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof PriceRange) {
            PriceRange otherRange = (PriceRange) other;
            return lowerBound.equals(otherRange.getLowerBound())
                    && upperBound.equals(otherRange.getUpperBound());
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(PriceRange o) {
        return this.lowerBound.compareTo(o.lowerBound);
    }
}
