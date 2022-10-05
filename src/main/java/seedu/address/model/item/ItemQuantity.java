package seedu.address.model.item;

import seedu.address.model.tag.Tag;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class ItemQuantity {
    public static final String MESSAGE_CONSTRAINTS = "Quantities must be greater than 0, and smaller than 10,000,000.";

    public final int itemQuantity;

    public ItemQuantity(int itemQuantity) {
        checkArgument(isValidItemQuantity(itemQuantity), MESSAGE_CONSTRAINTS);
        this.itemQuantity = itemQuantity;
    }

    public static boolean isValidItemQuantity(int itemQuantity) {
        return itemQuantity > 0 && itemQuantity < 10000000;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemQuantity // instanceof handles nulls
                && this.itemQuantity == ((ItemQuantity) other).itemQuantity); // state check
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return String.valueOf(itemQuantity);
    }
}
