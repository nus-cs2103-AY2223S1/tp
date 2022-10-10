package seedu.address.model.item.itemvalidator;

import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.item.Item;

/**
 * Validation class for item names.
 */
public class ItemUnitValidator {

    // Validation for characters used in unit
    // TODO: Change validation to match FoodREM
    // TODO: SHOULD ABSTRACT OUT LOGIC FROM ITEM NAME, UNIT, TAG NAME COMMON TO VALIDATION
    private static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    private static final String MESSAGE_FOR_INVALID_CHARACTERS =
            "Item unit should only contain alphanumeric characters and spaces, and it should not be blank";

    // Validation for unit length
    private static final int MAX_LENGTH = 20;
    private static final String MESSAGE_FOR_UNIT_TOO_LONG =
            String.format("Item unit should not exceed %d characters", MAX_LENGTH);
    private static final String MESSAGE_FOR_UNIT_IS_BLANK = "Item unit should not be left empty.";

    /**
     * Validates a given input String.
     *
     * @param unitString String representation of item unit to validate against.
     */
    public static void validate(String unitString) {
        checkArgument(doesUnitContainInvalidCharacters(unitString), MESSAGE_FOR_INVALID_CHARACTERS);
        checkArgument(isUnitLengthMoreThanMaxLength(unitString), MESSAGE_FOR_UNIT_TOO_LONG);
        checkArgument(isUnitBlank(unitString), MESSAGE_FOR_UNIT_IS_BLANK);
    }

    /**
     * Returns true if a given string contains invalid characters, false otherwise.
     *
     * @param itemUnit a string that represents the unit of the {@link Item}.
     */
    public static boolean doesUnitContainInvalidCharacters(String itemUnit) {
        return !itemUnit.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if an item unit has a length more than {@link ItemUnitValidator#MAX_LENGTH}, false otherwise.
     *
     * @param itemUnit a string that represents the unit of the {@link Item}.
     */
    public static boolean isUnitLengthMoreThanMaxLength(String itemUnit) {
        return itemUnit.length() > MAX_LENGTH;
    }

    /**
     * Returns true if a unit is {@link String#isEmpty()}, false otherwise.
     *
     * @param unitName a string that represents the name of the {@link Item}.
     */
    public static boolean isUnitBlank(String unitName) {
        return unitName.isEmpty();
    }
}
