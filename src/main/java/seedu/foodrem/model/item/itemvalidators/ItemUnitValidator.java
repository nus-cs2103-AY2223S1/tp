package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.commons.util.AppUtil.checkArgument;

import seedu.foodrem.model.item.Item;

/**
 * Validation class for item names.
 */
public class ItemUnitValidator implements Validator {

    public static final String MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT =
            "Item unit should only contain alphanumeric characters and spaces";
    // Validation for characters used in unit
    private static final String VALIDATION_REGEX = "[A-Za-z0-9 ]*";
    // Validation for unit length
    private static final int MAX_LENGTH = 10;
    private static final String MESSAGE_FOR_NAME_TOO_LONG =
            String.format("Item unit should not exceed %d characters", MAX_LENGTH);

    /**
     * Validates a given input String.
     *
     * @param unitString String representation of item unit to validate against.
     */
    public static void validate(String unitString) {
        checkArgument(isUnitContainingOnlyValidCharacters(unitString), MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);
        checkArgument(isUnitLengthLessThanEqualMaxLength(unitString), MESSAGE_FOR_NAME_TOO_LONG);
    }

    /**
     * Returns true if a given string contains invalid characters, false otherwise.
     *
     * @param itemUnit a string that represents the unit of the {@link Item}.
     */
    private static boolean isUnitContainingOnlyValidCharacters(String itemUnit) {
        return itemUnit.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if an item unit has a length less than or equal {@link ItemUnitValidator#MAX_LENGTH},
     * false otherwise.
     *
     * @param itemUnit a string that represents the unit of the {@link Item}.
     */
    private static boolean isUnitLengthLessThanEqualMaxLength(String itemUnit) {
        return itemUnit.length() <= MAX_LENGTH;
    }
}
