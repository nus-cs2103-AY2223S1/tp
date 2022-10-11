package seedu.address.model.order;

import java.util.Objects;

public class PriceRange {

    private Price upperBound;
    private Price lowerBound;

    public PriceRange(Price upperBound, Price lowerBound) {
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

    public void updatePriceRange(Price upperBound, Price lowerBound) {
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
