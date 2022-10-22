package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.commons.util.AppUtil.checkArgument;

/**
 * Validation class for item names.
 */
public class ItemUnitValidator implements Validator {
    // Validation for characters used in unit
    private static final String VALIDATION_REGEX = "[A-Za-z0-9 ]*";
    private static final String MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT =
            "The item unit should only contain alphanumeric characters and spaces.";

    // Validation for unit length
    private static final int MAX_LENGTH = 10;
    private static final String MESSAGE_FOR_UNIT_TOO_LONG =
            String.format("The item unit should not exceed %d characters.", MAX_LENGTH);

    /**
     * Validates a given input String.
     *
     * @param unitString String representation of item unit to validate against.
     */
    public static Void validate(String unitString) {
        boolean isUnitLengthLessThanEqualMaxLength = unitString.length() <= MAX_LENGTH;
        boolean isUnitContainingOnlyValidCharacters = unitString.matches(VALIDATION_REGEX);

        checkArgument(isUnitContainingOnlyValidCharacters, MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);
        checkArgument(isUnitLengthLessThanEqualMaxLength, MESSAGE_FOR_UNIT_TOO_LONG);
        return null;
    }
}
