package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.commons.util.AppUtil.checkArgument;

import seedu.foodrem.commons.util.ValidationUtil;

/**
 * Validation class for item quantities.
 */
public class ItemPriceValidator implements Validator {
    /** Validation for price precision */
    private static final int MAX_DECIMAL_PLACE = 2;
    /** Validation for size of price */
    private static final int MAX_PRICE = 1_000_000;

    /**
     * Validates a given input String. This is to be used during construction.
     *
     * @param itemPriceString String representation of item price to validate against.
     */
    public static Void validate(String itemPriceString) {
        return validateNumericString(itemPriceString, MAX_DECIMAL_PLACE, MAX_PRICE,
                "The item price should be a number.",
                String.format("The item price should not have more than %d decimal places.", MAX_DECIMAL_PLACE),
                String.format("The item price should not be more than %,d.", MAX_PRICE),
                "The item price should not be negative.");
    }

    static Void validateNumericString(String numericString, int maxDecimalPlace, int maximum,
                                      String messageNotANumber, String messageTooPrecise, String messageTooLarge,
                                      String messageIsNegative) {
        boolean isParsable = ValidationUtil.isParsableDouble(numericString);
        boolean hasNoSpecialChars = numericString.matches("[0-9.-]*");
        boolean isTooPrecise = ValidationUtil.isDoubleTooPrecise(numericString, maxDecimalPlace);

        checkArgument(isParsable && hasNoSpecialChars, messageNotANumber);
        checkArgument(!isTooPrecise, messageTooPrecise);

        double number = Double.parseDouble(numericString);
        boolean isWithinMaximum = number <= maximum;
        boolean isNonNegative = ValidationUtil.isNonNegative(number);

        checkArgument(isWithinMaximum, messageTooLarge);
        checkArgument(isNonNegative, messageIsNegative);
        return null;
    }
}
