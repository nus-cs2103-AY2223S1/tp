package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.commons.util.AppUtil.checkArgument;

import seedu.foodrem.model.item.Item;

/**
 * Validation class for item quantities.
 */
public class ItemQuantityValidator implements Validator {


    public static final String MESSAGE_FOR_NOT_A_NUMBER = "The item quantity should be a number.";
    public static final String MESSAGE_FOR_QUANTITY_IS_NEGATIVE =
            "The item quantity should not be negative.";

    // Validation for quantity precision
    private static final int MAX_DECIMAL_PLACE = 4;
    public static final String MESSAGE_FOR_PRECISION_TOO_HIGH =
            String.format("The item quantity should not have more than %d decimal places.", MAX_DECIMAL_PLACE);
    private static final String DECIMAL_POINT = ".";

    // Validation for size of quantity
    private static final int MAX_QUANTITY = 1_000_000;
    public static final String MESSAGE_FOR_QUANTITY_TOO_LARGE =
            String.format("The item quantity should not be more than %,d.", MAX_QUANTITY);

    /**
     * Validates a given input String. This is to be used during construction.
     *
     * @param itemQuantityString String representation of item quantity to validate against.
     */
    public static Void validate(String itemQuantityString) {
        checkArgument(isParsableQuantity(itemQuantityString), MESSAGE_FOR_NOT_A_NUMBER);
        checkArgument(!isQuantityTooPrecise(itemQuantityString), MESSAGE_FOR_PRECISION_TOO_HIGH);
        double quantity = Double.parseDouble(itemQuantityString);
        checkArgument(isQuantityLessThanEqualMaxQuantity(quantity), MESSAGE_FOR_QUANTITY_TOO_LARGE);
        checkArgument(isQuantityNotNegative(quantity), MESSAGE_FOR_QUANTITY_IS_NEGATIVE);
        return null;
    }

    /**
     * Returns true if an item quantity can be parsed, false otherwise.
     *
     * @param itemQuantityString a string that represents the quantity of the {@link Item}.
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
     * @param itemQuantityString a string that represents the quantity of the {@link Item}.
     */
    private static boolean isQuantityTooPrecise(String itemQuantityString) {
        if (!itemQuantityString.contains(DECIMAL_POINT)) {
            return false;
        }
        int numberOfDecimalPoints = itemQuantityString.length() - itemQuantityString.indexOf(DECIMAL_POINT) - 1;
        return numberOfDecimalPoints > MAX_DECIMAL_PLACE;
    }

    /**
     * Returns true if an item quantity is less than or equal to the {@link ItemQuantityValidator#MAX_QUANTITY},
     * false otherwise.
     *
     * @param itemQuantity a double that represents the quantity of the {@link Item}.
     */
    private static boolean isQuantityLessThanEqualMaxQuantity(double itemQuantity) {
        return itemQuantity <= MAX_QUANTITY;
    }

    /**
     * Returns true if an item quantity is positive or zero, false otherwise.
     *
     * @param itemQuantity a double that represents the quantity of the {@link Item}.
     */
    private static boolean isQuantityNotNegative(double itemQuantity) {
        return itemQuantity >= 0;
    }
}
