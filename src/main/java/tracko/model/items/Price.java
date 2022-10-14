package tracko.model.items;

import static tracko.commons.util.AppUtil.checkArgument;
import static tracko.commons.util.CollectionUtil.requireAllNonNull;

public class Price {
    public static final String MESSAGE_CONSTRAINTS =
            "Price should be non-negative.";

    public final float price;

    /**
     * Constructs a {@code Price}.
     * @param price The Price of an item.
     */
    public Price(int price) {
        requireAllNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        this.price = price;
    }

    public static boolean isValidPrice(int test) {
        return !(test < 0);
    }

    public Float getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return String.valueOf(price);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Price)) {
            return false;
        }

        Price otherPrice = (Price) other;
        return this.equals(otherPrice.getPrice());
    }
}
