package seedu.address.model.item.itemvalidator;

import seedu.address.model.item.ItemUnit;

import static seedu.address.commons.util.AppUtil.checkArgument;

public class ItemUnitValidator {

    // Validation for characters used in unit
    // TODO: Change validation to match FoodREM
    // TODO: SHOULD ABSTRACT OUT LOGIC FROM ITEM NAME, UNIT, TAG NAME COMMON TO VALIDATION
    private static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    private static final String MESSAGE_FOR_INVALID_CHARACTERS =
            "Item unit should only contain alphanumeric characters and spaces, and it should not be blank";

    // Validation for unit length
    private static final int MAX_LENGTH = 10;
    private static final String MESSAGE_FOR_NAME_TOO_LONG =
            String.format("Item unit should not exceed %d characters", MAX_LENGTH);


    public static void validate(String unitString) {
        checkArgument(doesUnitContainInvalidCharacters(unitString), MESSAGE_FOR_INVALID_CHARACTERS);
        checkArgument(isUnitLengthMoreThanMaxLength(unitString), MESSAGE_FOR_NAME_TOO_LONG);
    }

    /**
     * Returns true if a given string contains invalid characters, false otherwise.
     *
     * @param itemUnit a string that represents the {@link ItemUnit#itemUnit}.
     */
    private static boolean doesUnitContainInvalidCharacters(String itemUnit) {
        return !itemUnit.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if an item unit has a length more than {@link ItemUnitValidator#MAX_LENGTH}, false otherwise.
     *
     * @param itemUnit a string that represents the {@link ItemUnit#itemUnit}.
     */
    private static boolean isUnitLengthMoreThanMaxLength(String itemUnit) {
        return itemUnit.length() > MAX_LENGTH;
    }
}
