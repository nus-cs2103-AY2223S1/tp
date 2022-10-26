package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.commons.util.AppUtil.checkArgument;

import seedu.foodrem.commons.util.StringUtil;

/**
 * Validation class for item remarks.
 */
public class ItemRemarksValidator implements Validator {
    // Validation for characters in remarks
    private static final String MESSAGE_FOR_INVALID_CHARACTERS_IN_REMARKS =
            "The item remark should only contain alphanumeric characters, spaces and the following symbols "
                    + "[]{}()-+*=.,_'\"^$?@!#%&:;";

    // Validation for remarks length
    private static final int MAX_LENGTH = 1000;
    private static final String MESSAGE_FOR_REMARKS_TOO_LONG =
            String.format("The item remark should not exceed %d characters", MAX_LENGTH);

    /**
     * Validates a given input String.This is to be used during construction.
     *
     * @param itemRemarks String representation of item remarks to validate against.
     */
    public static Void validate(String itemRemarks) {
        boolean isRemarksContainingOnlyValidCharacters = itemRemarks.matches(StringUtil.VALIDATION_REGEX);
        boolean isRemarksLengthLessThanEqualMaxLength = itemRemarks.length() <= MAX_LENGTH;

        checkArgument(isRemarksContainingOnlyValidCharacters, MESSAGE_FOR_INVALID_CHARACTERS_IN_REMARKS);
        checkArgument(isRemarksLengthLessThanEqualMaxLength, MESSAGE_FOR_REMARKS_TOO_LONG);
        return null;
    }
}
