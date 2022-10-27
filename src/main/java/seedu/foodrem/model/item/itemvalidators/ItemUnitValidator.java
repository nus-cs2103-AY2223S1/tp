package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.commons.util.AppUtil.checkArgument;

import seedu.foodrem.commons.util.StringUtil;

/**
 * Validation class for item names.
 */
public class ItemUnitValidator implements Validator {
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

        checkArgument(StringUtil.isValidString(unitString), StringUtil.getInvalidCharactersMessage("item unit"));
        checkArgument(isUnitLengthLessThanEqualMaxLength, MESSAGE_FOR_UNIT_TOO_LONG);
        return null;
    }
}
