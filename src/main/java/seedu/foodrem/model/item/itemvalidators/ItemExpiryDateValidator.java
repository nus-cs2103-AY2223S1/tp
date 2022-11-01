package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;

import seedu.foodrem.commons.util.ValidationUtil;
import seedu.foodrem.model.item.ItemDate;

/**
 * Validation class for item dates.
 */
public class ItemExpiryDateValidator implements Validator {
    // Validation for parsing
    private static final String MESSAGE_FOR_EXPIRY_DATE_NOT_DDMMYYYY =
            "The item expiry date must follow the format dd-mm-yyyy.";
    private static final String MESSAGE_FOR_EXPIRY_DATE_DO_NOT_EXIST =
            "The item expiry date does not exist.";

    // Validation for year
    private static final int MIN_YEAR = 1900;
    private static final String MESSAGE_FOR_YEAR_TOO_SMALL =
            String.format("The year for item expiry date should be larger than or equal to %d.", MIN_YEAR);

    private static final int MAX_YEAR = 2300;
    private static final String MESSAGE_FOR_YEAR_TOO_LARGE =
            String.format("The year for item expiry date should be less than or equal to %d.", MAX_YEAR);

    /**
     * Validates a given input String.
     *
     * @param dateString String representation of date to validate against.
     */
    public static Void validate(String dateString) {
        boolean isDateStringInRightFormat = dateString.matches("[0-9][0-9]-[0-9][0-9]-[0-9][0-9][0-9][0-9]");

        boolean isDateStringParsable = ValidationUtil.isParsableDateString(dateString, ItemDate.DATE_PATTERN_REGEX);
        checkArgument(isDateStringInRightFormat, MESSAGE_FOR_EXPIRY_DATE_NOT_DDMMYYYY);
        checkArgument(isDateStringParsable, MESSAGE_FOR_EXPIRY_DATE_DO_NOT_EXIST);

        LocalDate date = LocalDate.parse(dateString, ItemDate.DATE_FORMATTER);

        boolean isYearMoreThanEqualToMinYear = date.getYear() >= MIN_YEAR;
        boolean isYearLessThanEqualToMaxYear = date.getYear() <= MAX_YEAR;

        checkArgument(isYearLessThanEqualToMaxYear, MESSAGE_FOR_YEAR_TOO_LARGE);
        checkArgument(isYearMoreThanEqualToMinYear, MESSAGE_FOR_YEAR_TOO_SMALL);
        return null;
    }
}
