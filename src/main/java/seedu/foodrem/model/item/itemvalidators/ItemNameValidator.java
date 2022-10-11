package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.commons.util.AppUtil.checkArgument;

import seedu.foodrem.model.item.Item;

/**
 * Validation class for item names.
 */
public class ItemNameValidator implements Validator {

    // Validation for characters in name
    public static final String MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME =
            "Item name should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String MESSAGE_FOR_NAME_IS_BLANK =
            "Item name should not be blank";
    private static final String VALIDATION_REGEX = "[A-Za-z0-9 ]*";

    // Validation for name length
    private static final int MAX_LENGTH = 200;
    public static final String MESSAGE_FOR_NAME_TOO_LONG =
            String.format("The item name should not exceed %d characters", MAX_LENGTH);
    private static final String MESSAGE_FOR_NAME_IS_BLANK =
            "Item name should not be blank";

    /**
     * Validates a given input String.This is to be used during construction.
     *
     * @param itemName String representation of item name to validate against.
     */
    public static void validate(String itemName) {
        checkArgument(isNameContainingOnlyValidCharacters(itemName), MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME);
        checkArgument(isNameLengthLessThanEqualMaxLength(itemName), MESSAGE_FOR_NAME_TOO_LONG);
        checkArgument(isNamePresent(itemName), MESSAGE_FOR_NAME_IS_BLANK);
    }

    /**
     * Returns true if a given string contains invalid characters, false otherwise.
     *
     * @param itemName a string that represents the name of the {@link Item}.
     */
    private static boolean isNameContainingOnlyValidCharacters(String itemName) {
        return itemName.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if an item name has a length less than or equal {@link ItemNameValidator#MAX_LENGTH},
     * false otherwise.
     *
     * @param itemName a string that represents the name of the {@link Item}.
     */
    private static boolean isNameLengthLessThanEqualMaxLength(String itemName) {
        return itemName.length() <= MAX_LENGTH;
    }

    /**
     * Returns true if an item name is return false for {@link String#isBlank()}, false otherwise.
     *
     * @param itemName a string that represents the name of the {@link Item}.
     */
    private static boolean isNamePresent(String itemName) {
        return !itemName.isBlank();
    }
}
