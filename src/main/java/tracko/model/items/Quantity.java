package tracko.model.items;

import static tracko.commons.util.AppUtil.checkArgument;
import static tracko.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents the quantity of an item.
 */
public class Quantity {
    public static final String MESSAGE_CONSTRAINTS = "Quantity should be valid. " +
            "\nA valid Quantity value should not be negative, and should not exceed 2,147,483,647.";

    public final Integer quantity;

    /**
     * Constructs a {@code Quantity}.
     * @param quantity The quantity of an item.
     */
    public Quantity(Integer quantity) {
        requireAllNonNull(quantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        this.quantity = quantity;
    }

    public static boolean isValidQuantity(Integer test) {
        return !(test < 0);
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    @Override
    public String toString() {
        return String.valueOf(quantity);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Quantity)) {
            return false;
        }

        Quantity otherQuantity = (Quantity) other;
        return this.quantity.equals(otherQuantity.getQuantity());
    }
}
