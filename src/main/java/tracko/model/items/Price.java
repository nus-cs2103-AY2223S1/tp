package tracko.model.items;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static tracko.commons.util.AppUtil.checkArgument;
import static tracko.commons.util.CollectionUtil.requireAllNonNull;

public class Price {
    public static final String MESSAGE_CONSTRAINTS =
            "Price should be non-negative.";

    public final BigDecimal price;

    /**
     * Constructs a {@code Price}.
     * @param price The Price of an item.
     */
    public Price(String price) {
        requireAllNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        this.price = new BigDecimal(price);
        roundToNearestCent(this.price);
    }

    public static boolean isValidPrice(String test) {
        return !(Float.parseFloat(test) < 0);
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public BigDecimal roundToNearestCent(BigDecimal price) {
        return price.setScale(2, RoundingMode.HALF_EVEN);
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
        return this.price.equals(otherPrice.getPrice());
    }
}
