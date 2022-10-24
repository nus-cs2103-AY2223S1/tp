package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.model.item.itemvalidators.ItemPriceValidator.validateNumericString;

/**
 * Validation class for item quantities.
 */
public class ItemQuantityValidator implements Validator {
    // Validation for characters used in quantity
    private static final String MESSAGE_FOR_NOT_A_NUMBER =
            "The item quantity should be a number.";
    private static final String MESSAGE_FOR_QUANTITY_IS_NEGATIVE =
            "The item quantity should not be negative.";

    // Validation for quantity precision
    private static final int MAX_DECIMAL_PLACE = 4;
    private static final String MESSAGE_FOR_PRECISION_TOO_HIGH =
            String.format("The item quantity should not have more than %d decimal places.", MAX_DECIMAL_PLACE);

    // Validation for size of quantity
    private static final int MAX_QUANTITY = 1_000_000;
    private static final String MESSAGE_FOR_QUANTITY_TOO_LARGE =
            String.format("The item quantity should not be more than %,d.", MAX_QUANTITY);

    /**
     * Validates a given input String. This is to be used during construction.
     *
     * @param itemQuantityString String representation of item quantity to validate against.
     */
    public static Void validate(String itemQuantityString) {
        return validateNumericString(itemQuantityString, MAX_DECIMAL_PLACE, MESSAGE_FOR_NOT_A_NUMBER,
                MESSAGE_FOR_PRECISION_TOO_HIGH, MAX_QUANTITY, MESSAGE_FOR_QUANTITY_TOO_LARGE,
                MESSAGE_FOR_QUANTITY_IS_NEGATIVE);
    }
}
