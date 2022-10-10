package seedu.address.model.item.validators;

import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.item.Item;

/**
 * Validation class for item quantities.
 */
public class ItemQuantityValidator {

    // Validation for quantity is a number
    private static final String MESSAGE_FOR_NOT_A_NUMBER = "Quantities should be a number.";

    // Validation for quantity precision
    private static final int MAX_DECIMAL_PLACE = 4;
    private static final String DECIMAL_POINT = ".";
    private static final String MESSAGE_FOR_PRECISION_TOO_HIGH =
            String.format("Quantity should not have more than %d decimal places", MAX_DECIMAL_PLACE);

    // Validation for size of quantity
    private static final int MAX_QUANTITY = 100000;
    private static final String MESSAGE_FOR_QUANTITY_TOO_LARGE =
            String.format("Quantity should not be more than %d.", MAX_QUANTITY);
    private static final String MESSAGE_FOR_QUANTITY_IS_NEGATIVE =
            "Quantity should not be negative.";

    /**
     * Validates a given input String.
     *
     * @param itemQuantityString String representation of item quantity to validate against.
     */
    public static void validate(String itemQuantityString) {
        checkArgument(isParsableQuantity(itemQuantityString), MESSAGE_FOR_NOT_A_NUMBER);
        checkArgument(isQuantityTooPrecise(itemQuantityString), MESSAGE_FOR_PRECISION_TOO_HIGH);

        double quantity = Double.parseDouble(itemQuantityString);
        checkArgument(isQuantityMoreThanMaxQuantity(quantity), MESSAGE_FOR_QUANTITY_TOO_LARGE);
        checkArgument(isQuantityNegative(quantity), MESSAGE_FOR_QUANTITY_IS_NEGATIVE);
    }

    /**
     * Returns true if an item quantity can be parsed, false otherwise.
     *
     * @param itemQuantityString a string that represents the quantity of the {@link Item}.
     */
    public static boolean isParsableQuantity(String itemQuantityString) {
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
     * @param itemQuantityString a string that represents the quantity of the {@link Item}.
     */
    public static boolean isQuantityTooPrecise(String itemQuantityString) {
        if (!itemQuantityString.contains(DECIMAL_POINT)) {
            return false;
        }
        int numberOfDecimalPoints = itemQuantityString.length() - itemQuantityString.indexOf(DECIMAL_POINT) - 1;
        return numberOfDecimalPoints > MAX_DECIMAL_PLACE;
    }

    /**
     * Returns true if an item quantity is more than the {@link ItemQuantityValidator#MAX_QUANTITY}, false otherwise.
     *
     * @param itemQuantity a double that represents the quantity of the {@link Item}.
     */
    public static boolean isQuantityMoreThanMaxQuantity(double itemQuantity) {
        return itemQuantity > MAX_QUANTITY;
    }

    /**
     * Returns true if an item quantity is negative, false otherwise.
     *
     * @param itemQuantity a double that represents the quantity of the {@link Item}.
     */
    public static boolean isQuantityNegative(double itemQuantity) {
        return itemQuantity < 0;
    }
}
