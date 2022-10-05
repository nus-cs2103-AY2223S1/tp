package seedu.address.model.order;

import java.util.Objects;

public class PriceRange {

    private double upperBound;
    private double lowerBound;

    public PriceRange(double upperBound, double lowerBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public void setUpperBound(double upperBound) {
        this.upperBound = upperBound;
    }

    public void setLowerBound(double lowerBound) {
        this.lowerBound = lowerBound;
    }

    public void updatePriceRange(double upperBound, double lowerBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }

    @Override
    public String toString() {
        return "Upper bound: " + upperBound + "; Lower bound: " + lowerBound;
    }

    @Override
    public int hashCode() {
        return Objects.hash(upperBound, lowerBound);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof PriceRange) {
            PriceRange priceRange = (PriceRange) other;
            return (lowerBound == priceRange.lowerBound) && (upperBound == priceRange.upperBound);
        } else {
            return false;
        }
    }

}
