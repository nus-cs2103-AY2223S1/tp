package seedu.address.model.order;

public class Price {

    private static final double NOT_APPLICABLE_PRICE = -1;
    private double price;

    public Price(double price) {
        this.price = price;
    }

    public static Price getNotApplicablePrice() {
        return new Price(NOT_APPLICABLE_PRICE);
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
        return Double.toString(price);
    }

}
