package tracko.model.items;

import static tracko.commons.util.AppUtil.checkArgument;
import static tracko.commons.util.CollectionUtil.requireAllNonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *  Represents the price of an item.
 */
public class Price {
    public static final String MESSAGE_CONSTRAINTS =
            "Price should be non-negative.";

    public final BigDecimal price;

    /**
     * Constructs a {@code Price}.
     * @param price The Price of an item.
     */
    public Price(BigDecimal price) {
        requireAllNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        this.price = roundToNearestCent(price);
    }

    public static boolean isValidPrice(BigDecimal test) {
        return !(test.compareTo(BigDecimal.ZERO) < 0);
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
