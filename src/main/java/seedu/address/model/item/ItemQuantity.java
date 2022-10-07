package seedu.address.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an item quantity in an {@link Item}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemQuantity {

    public final double itemQuantity;

    private static final double DEFAULT_QUANTITY = 0;

    // Validation for quantity is a number
    private static final String MESSAGE_FOR_NOT_A_NUMBER = "Quantities should be a number.";

    // Validation for quantity precision
    private static final int MAX_DECIMAL_PLACE = 4;
    private static final String DECIMAL_POINT = ".";
    private static final String MESSAGE_FOR_PRECISION_TOO_HIGH =
            String.format("Quantity should not have more than %d decimal places", MAX_DECIMAL_PLACE);

    // Validation for size of quantity
    private static final int MAX_QUANTITY = 1000000;
    private static final String MESSAGE_FOR_QUANTITY_TOO_LARGE =
            String.format("Quantity should not be more than %d.", MAX_QUANTITY);
    private static final String MESSAGE_FOR_QUANTITY_IS_NEGATIVE =
            "Quantity should not be negative.";


    public ItemQuantity(String itemQuantityString) {
        requireNonNull(itemQuantityString);
        if (itemQuantityString.isEmpty()) {
            itemQuantity = DEFAULT_QUANTITY;
            return;
        }

        checkArgument(isParsableQuantity(itemQuantityString), MESSAGE_FOR_NOT_A_NUMBER);
        checkArgument(isQuantityTooPrecise(itemQuantityString), MESSAGE_FOR_PRECISION_TOO_HIGH);

        double quantity = Double.parseDouble(itemQuantityString);
        checkArgument(isQuantityMoreThanMaxQuantity(quantity), MESSAGE_FOR_QUANTITY_TOO_LARGE);
        checkArgument(isQuantityNegative(quantity), MESSAGE_FOR_QUANTITY_IS_NEGATIVE);

        itemQuantity = quantity;
    }

    /**
     * Returns true if an item quantity can be parsed, false otherwise.
     *
     * @param itemQuantityString a string that represents the {@link ItemQuantity#itemQuantity}.
     */
    private static boolean isParsableQuantity(String itemQuantityString) {
        try {
            Double.parseDouble(itemQuantityString);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if an item quantity is too precise, false otherwise.
     *
     * @param itemQuantityString a string that represents the {@link ItemQuantity#itemQuantity}.
     */
    private static boolean isQuantityTooPrecise(String itemQuantityString) {
        if (!itemQuantityString.contains(DECIMAL_POINT)) {
            return true;
        }
        int numberOfDecimalPoints = itemQuantityString.length() - itemQuantityString.indexOf(DECIMAL_POINT);
        return numberOfDecimalPoints > MAX_DECIMAL_PLACE;
    }

    /**
     * Returns true if an item quantity is more than the {@link ItemQuantity#MAX_QUANTITY}, false otherwise.
     *
     * @param itemQuantity a double that represents the {@link ItemQuantity#itemQuantity}.
     */
    private static boolean isQuantityMoreThanMaxQuantity(double itemQuantity) {
        return itemQuantity > MAX_QUANTITY;
    }

    /**
     * Returns true if an item quantity is negative, false otherwise.
     *
     * @param itemQuantity a double that represents the {@link ItemQuantity#itemQuantity}.
     */
    private static boolean isQuantityNegative(double itemQuantity) {
        return itemQuantity < 0;
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
