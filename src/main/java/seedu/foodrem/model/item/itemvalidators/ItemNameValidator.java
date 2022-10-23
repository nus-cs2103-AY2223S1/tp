package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.commons.util.AppUtil.checkArgument;

/**
 * Validation class for item names.
 */
public class ItemNameValidator implements Validator {
    // Validation for characters in name
    private static final String VALIDATION_REGEX = "[A-Za-z0-9 ]*";
    private static final String MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME =
            "The item name should only contain alphanumeric characters and spaces. "
                    + "It should not start with a blank space.";
    private static final String MESSAGE_FOR_NAME_IS_BLANK =
            "The item name should not be blank.";

    // Validation for name length
    private static final int MAX_LENGTH = 200;
    private static final String MESSAGE_FOR_NAME_TOO_LONG =
            String.format("The item name should not exceed %d characters", MAX_LENGTH);

    /**
     * Validates a given input String.This is to be used during construction.
     *
     * @param itemName String representation of item name to validate against.
     */
    public static Void validate(String itemName) {
        boolean isNameContainingOnlyValidCharacters = itemName.matches(VALIDATION_REGEX);
        boolean isNameLengthLessThanEqualMaxLength = itemName.length() <= MAX_LENGTH;
        boolean isNamePresent = !itemName.isBlank();

        checkArgument(isNameContainingOnlyValidCharacters, MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME);
        checkArgument(isNameLengthLessThanEqualMaxLength, MESSAGE_FOR_NAME_TOO_LONG);
        checkArgument(isNamePresent, MESSAGE_FOR_NAME_IS_BLANK);
        return null;
    }
}
