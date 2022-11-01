package tracko.model.item;

import static tracko.commons.util.AppUtil.checkArgument;
import static tracko.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents the quantity of an item.
 */
public class Quantity {
    public static final String MESSAGE_CONSTRAINTS = "Quantity should be valid."
            + "\nA valid Quantity value should be numeric, non-empty, "
            + "non-negative, and should not exceed 2,147,483,647.";


    public final Integer value;

    /**
     * Constructs a {@code Quantity}.
     * @param value The quantity of an item.
     */
    public Quantity(Integer value) {
        requireAllNonNull(value);
        checkArgument(isValidQuantity(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    public static boolean isValidQuantity(Integer test) {
        return !(test < 0);
    }

    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
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
        return this.value.equals(otherQuantity.getValue());
    }
}
