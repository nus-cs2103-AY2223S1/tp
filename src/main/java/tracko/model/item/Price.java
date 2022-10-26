package tracko.model.item;

import static tracko.commons.util.AppUtil.checkArgument;
import static tracko.commons.util.CollectionUtil.requireAllNonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *  Represents the price of an item.
 */
public class Price {
    public static final String MESSAGE_CONSTRAINTS =
            "Price should be non-negative and rounded to the nearest cent.";

    public final Double value;

    /**
     * Constructs a {@code Price}.
     * @param value The Price of an item.
     */
    public Price(Double value) {
        requireAllNonNull(value);
        checkArgument(isValidPrice(value), MESSAGE_CONSTRAINTS);
        this.value = roundToNearestCent(value);
    }

    /**
     * Checks if {@code test} is a valid Price.
     * @param test The price to validate.
     * @return True if input is a valid price
     */
    public static boolean isValidPrice(Double test) {
        boolean isPositive = !(BigDecimal.valueOf(test).compareTo(BigDecimal.ZERO) < 0);
        boolean isRoundedTo2dp = (BigDecimal.valueOf(test).scale() <= 2);
        return isPositive && isRoundedTo2dp;
    }

    public Double getValue() {
        return this.value;
    }

    /**
     * Rounds price to the nearest cent.
     * @param price Price to be rounded to the nearest cent.
     * @return Double Representing price rounded to the nearest cent.
     */
    public Double roundToNearestCent(Double price) {
        BigDecimal bigDecimalPrice = new BigDecimal(price);
        bigDecimalPrice.setScale(2, RoundingMode.HALF_UP);
        return bigDecimalPrice.doubleValue();
    }

    @Override
    public String toString() {
        return String.format("%.2f", value);
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
        return (this.value.equals(otherPrice.getValue()));
    }
}
