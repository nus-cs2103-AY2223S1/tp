package seedu.address.model.order;

import java.util.Objects;

/**
 * Represents the price range (lower bound, upper bound) of an order during negotiation.
 */
public class PriceRange {

    public static final String DELIMITER = ",";
    public static final String MESSAGE_USAGE =
            "The price range must be two non-negative decimal numbers, separated by a comma. "
            + "For example, 0.8,2.1. The left should be smaller."
            + "If you have not decided one bound, you can enter " + Price.NOT_APPLICABLE_PRICE
            + " to indicate non-applicable price. "
            + "For example, 0.7,-1 means the price is at least 0.7 but not bounded above. ";

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

    @Override
    public String toString() {
        return " " + lowerBound + " - " + upperBound;
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

}
