package seedu.address.model.item;

import seedu.address.model.item.itemvalidator.ItemQuantityValidator;

import static java.util.Objects.requireNonNull;

/**
 * Represents an item quantity in an {@link Item}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemQuantity {

    public final double itemQuantity;

    private static final double DEFAULT_QUANTITY = 0;

    public ItemQuantity(String itemQuantityString) {
        requireNonNull(itemQuantityString);
        if (itemQuantityString.isEmpty()) {
            itemQuantity = DEFAULT_QUANTITY;
            return;
        }
        ItemQuantityValidator.validate(itemQuantityString);
        itemQuantity = Double.parseDouble(itemQuantityString);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemQuantity // instanceof handles nulls
                && itemQuantity == ((ItemQuantity) other).itemQuantity); // state check
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.valueOf(itemQuantity);
    }
}
