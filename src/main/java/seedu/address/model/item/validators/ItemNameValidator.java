package seedu.address.model.item.validators;

import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.item.Item;

/**
 * Validation class for item names.
 */
public class ItemNameValidator {

    // Validation for characters used in name
    // TODO: Change validation to match FoodREM
    private static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    private static final String MESSAGE_FOR_INVALID_CHARACTERS =
            "Item name should only contain alphanumeric characters and spaces, and it should not be blank";

    // Validation for name length
    private static final int MAX_LENGTH = 200;
    private static final String MESSAGE_FOR_NAME_TOO_LONG =
            String.format("Item name should not exceed %d characters", MAX_LENGTH);
    private static final String MESSAGE_FOR_NAME_IS_BLANK =
            "Item name should not be blank";

    /**
     * Validates a given input String.
     *
     * @param itemName String representation of item name to validate against.
     */
    public static void validate(String itemName) {
        checkArgument(doesNameContainInvalidCharacters(itemName), MESSAGE_FOR_INVALID_CHARACTERS);
        checkArgument(isNameLengthMoreThanMaxLength(itemName), MESSAGE_FOR_NAME_TOO_LONG);
        checkArgument(isNameBlank(itemName), MESSAGE_FOR_NAME_IS_BLANK);
    }

    /**
     * Returns true if a given string contains invalid characters, false otherwise.
     *
     * @param itemName a string that represents the name of the {@link Item}.
     */
    public static boolean doesNameContainInvalidCharacters(String itemName) {
        return !itemName.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if an item name has a length more than {@link ItemNameValidator#MAX_LENGTH}, false otherwise.
     *
     * @param itemName a string that represents the name of the {@link Item}.
     */
    public static boolean isNameLengthMoreThanMaxLength(String itemName) {
        return itemName.length() > MAX_LENGTH;
    }

    /**
     * Returns true if an item name is {@link String#isEmpty()}, false otherwise.
     *
     * @param itemName a string that represents the name of the {@link Item}.
     */
    public static boolean isNameBlank(String itemName) {
        return itemName.isEmpty();
    }
}
