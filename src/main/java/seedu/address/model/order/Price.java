package seedu.address.model.order;

/**
 * Represents the final settled price of an order.
 */
public class Price implements Comparable<Price> {

    public static final double NOT_APPLICABLE_PRICE = -1;
    public static final String MESSAGE_USAGE =
            "The price should be a non-negative decimal number, such as 0.3, 9.8 etc.\n"
                    + "If you have not decided the price yet, enter "
                    + NOT_APPLICABLE_PRICE
                    + " to indicate a non-applicable price";

    private double price;

    /**
     * Creates a Price.
     */
    public Price(double price) {
        this.price = price;
    }

    public static Price getNotApplicablePrice() {
        return new Price(NOT_APPLICABLE_PRICE);
    }

    public boolean isNotApplicablePrice() {
        return this.price == NOT_APPLICABLE_PRICE;
    }

    public static boolean isNotApplicablePrice(Price price) {
        return price.price == NOT_APPLICABLE_PRICE;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Price) {
            Price otherPrice = (Price) other;
            return price == otherPrice.price;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Double.hashCode(price);
    }

    @Override
    public String toString() {
        String outputString = price == NOT_APPLICABLE_PRICE
                ? "The price is not settled yet"
                : Double.toString(price);
        return "Price settled: " + outputString;
    }

    @Override
    public int compareTo(Price other) {
        if (other.price == NOT_APPLICABLE_PRICE || this.price == NOT_APPLICABLE_PRICE) {
            return -1;
        }
        return Double.compare(this.price, other.price);
    }
}
