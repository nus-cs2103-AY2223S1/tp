package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.commons.util.AppUtil.checkArgument;

import seedu.foodrem.commons.util.ValidationUtil;

/**
 * Validation class for item quantities.
 */
public class ItemPriceValidator implements Validator {
    // Validation for characters used in price
    private static final String MESSAGE_FOR_NOT_A_NUMBER =
            "The item price should be a number.";
    private static final String MESSAGE_FOR_PRICE_IS_NEGATIVE =
            "The item price should not be negative.";

    // Validation for price precision
    private static final int MAX_DECIMAL_PLACE = 2;
    private static final String MESSAGE_FOR_PRECISION_TOO_HIGH =
            String.format("The item price should not have more than %d decimal places.", MAX_DECIMAL_PLACE);

    // Validation for size of price
    private static final int MAX_PRICE = 1_000_000;
    private static final String MESSAGE_FOR_PRICE_TOO_LARGE =
            String.format("The item price should not be more than %,d.", MAX_PRICE);

    /**
     * Validates a given input String. This is to be used during construction.
     *
     * @param itemPriceString String representation of item price to validate against.
     */
    public static Void validate(String itemPriceString) {
        return validateNumericString(itemPriceString, MAX_DECIMAL_PLACE, MESSAGE_FOR_NOT_A_NUMBER,
                MESSAGE_FOR_PRECISION_TOO_HIGH, MAX_PRICE, MESSAGE_FOR_PRICE_TOO_LARGE, MESSAGE_FOR_PRICE_IS_NEGATIVE);
    }

    static Void validateNumericString(String numericString, int maxDecimalPlace, String messageNotANumber,
                                      String messageTooPrecise, int maximum, String messageTooLarge,
                                      String messageIsNegative) {
        boolean isParsable = ValidationUtil.isParsableDouble(numericString);
        boolean isTooPrecise = ValidationUtil.isDoubleTooPrecise(numericString, maxDecimalPlace);

        checkArgument(isParsable, messageNotANumber);
        checkArgument(!isTooPrecise, messageTooPrecise);

        double number = Double.parseDouble(numericString);

        boolean isWithinMaximum = number <= maximum;
        boolean isNonNegative = ValidationUtil.isNonNegative(number);

        checkArgument(isWithinMaximum, messageTooLarge);
        checkArgument(isNonNegative, messageIsNegative);
        return null;
    }
}
