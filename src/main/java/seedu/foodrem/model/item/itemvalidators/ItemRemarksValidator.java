package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.commons.util.AppUtil.checkArgument;

import seedu.foodrem.commons.util.StringUtil;

/**
 * Validation class for item remarks.
 */
public class ItemRemarksValidator implements Validator {
    // Validation for remarks length
    // TODO: Ensure UG is standardised
    private static final int MAX_LENGTH = 10000;
    private static final String MESSAGE_FOR_REMARKS_TOO_LONG =
            String.format("The item remark should not exceed %d characters", MAX_LENGTH);

    /**
     * Validates a given input String.This is to be used during construction.
     *
     * @param itemRemarks String representation of item remarks to validate against.
     */
    public static Void validate(String itemRemarks) {
        boolean isRemarksLengthLessThanEqualMaxLength = itemRemarks.length() <= MAX_LENGTH;

        checkArgument(StringUtil.isValidString(itemRemarks),
                      StringUtil.getInvalidCharactersMessage("item remark"));
        checkArgument(isRemarksLengthLessThanEqualMaxLength, MESSAGE_FOR_REMARKS_TOO_LONG);
        return null;
    }
}
