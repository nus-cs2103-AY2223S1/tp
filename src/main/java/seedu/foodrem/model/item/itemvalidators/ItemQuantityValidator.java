package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.model.item.itemvalidators.ItemPriceValidator.validateNumericString;

/**
 * Validation class for item quantities.
 */
public class ItemQuantityValidator implements Validator {
    // Validation for quantity precision
    private static final int MAX_DECIMAL_PLACE = 4;
    // Validation for size of quantity
    private static final int MAX_QUANTITY = 1_000_000;

    /**
     * Validates a given input String. This is to be used during construction.
     *
     * @param itemQuantityString String representation of item quantity to validate against.
     */
    public static Void validate(String itemQuantityString) {
        return validateNumericString(itemQuantityString, MAX_DECIMAL_PLACE, MAX_QUANTITY,
                "The item quantity should be a number.",
                String.format("The item quantity should not have more than %d decimal places.", MAX_DECIMAL_PLACE),
                String.format("The item quantity should not be more than %,d.", MAX_QUANTITY),
                "The item quantity should not be negative.");
    }
}
