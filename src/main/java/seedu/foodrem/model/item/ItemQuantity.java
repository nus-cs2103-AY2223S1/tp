package seedu.foodrem.model.item;

import static java.util.Objects.requireNonNull;

import seedu.foodrem.model.item.itemvalidator.ItemQuantityValidator;


/**
 * Represents an item quantity in an {@link Item}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemQuantity {

    private static final double DEFAULT_QUANTITY = 0;

    private final double itemQuantity;

    /**
     * {@inheritDoc}
     */
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
     * Overloaded constructor that returns an ItemQuantity from a specified item quantity (double).
     */
    public ItemQuantity(double itemQuantityDouble) {
        requireNonNull(itemQuantityDouble);
        // TODO: Add validation
        this.itemQuantity = itemQuantityDouble;
    }

    /**
     * Factory method that increments the ItemQuantity by a specified amount and returns a new ItemQuantity object
     * with the quantity incremented.
     *
     * @param increment ItemQuantity amount to increment by.
     * @return New ItemQuantity with quantity incremented.
     */
    public ItemQuantity incrementQuantity(ItemQuantity increment) {
        // TODO: Needs validation to ensure item can be incremented with a valid amount, and final result is not
        //  beyond the boundaries provided.
        return new ItemQuantity(increment.itemQuantity + itemQuantity);
    }

    /**
     * Factory method that decrements the ItemQuantity by a specified amount and returns a new ItemQuantity object
     * with the quantity decremented..
     *
     * @param increment ItemQuantity amount to decrement by.
     * @return New ItemQuantity with quantity decremented..
     */
    public ItemQuantity decrementQuantity(ItemQuantity increment) {
        // TODO: Needs validation to ensure item can be incremented with a valid amount, and final result is not
        //  beyond the boundaries provided.
        return new ItemQuantity(itemQuantity - increment.itemQuantity);
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
