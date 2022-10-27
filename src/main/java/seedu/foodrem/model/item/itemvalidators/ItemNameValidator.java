package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.commons.util.AppUtil.checkArgument;

import seedu.foodrem.commons.util.StringUtil;

/**
 * Validation class for item names.
 */
public class ItemNameValidator implements Validator {
    // Validation for characters in name
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
        boolean isNameLengthLessThanEqualMaxLength = itemName.length() <= MAX_LENGTH;
        boolean isNamePresent = !itemName.isBlank();

        checkArgument(StringUtil.isValidString(itemName), StringUtil.getInvalidCharactersMessage("item name"));
        checkArgument(isNameLengthLessThanEqualMaxLength, MESSAGE_FOR_NAME_TOO_LONG);
        checkArgument(isNamePresent, MESSAGE_FOR_NAME_IS_BLANK);
        return null;
    }
}
