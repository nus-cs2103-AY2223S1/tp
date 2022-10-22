package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.commons.util.AppUtil.checkArgument;
import static seedu.foodrem.model.item.ItemExpiryDate.EXPIRY_DATE_FORMATTER;
import static seedu.foodrem.model.item.ItemExpiryDate.EXPIRY_DATE_PATTERN_REGEX;

import java.time.LocalDate;

import seedu.foodrem.commons.util.ValidationUtil;

/**
 * Validation class for item dates.
 */
public class ItemExpiryDateValidator implements Validator {

    // Validation for parsing
    private static final String MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE =
            "The item expiry date must follow the format dd-mm-yyyy.";

    // Validation for year
    private static final int MIN_YEAR = 1900;
    private static final String MESSAGE_FOR_YEAR_TOO_SMALL =
            String.format("The year for item expiry date should be larger than %d.", MIN_YEAR);

    private static final int MAX_YEAR = 2300;
    private static final String MESSAGE_FOR_YEAR_TOO_LARGE =
            String.format("The year for item expiry date should be less than %d.", MAX_YEAR);

    /**
     * Validates a given input String.
     *
     * @param dateString String representation of date to validate against.
     */
    public static Void validate(String dateString) {
        boolean isDateStringParsable = ValidationUtil.isParsableDateString(dateString, EXPIRY_DATE_PATTERN_REGEX);
        checkArgument(isDateStringParsable, MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE);

        LocalDate date = LocalDate.parse(dateString, EXPIRY_DATE_FORMATTER);

        boolean isYearMoreThanEqualToMinYear = date.getYear() >= MIN_YEAR;
        boolean isYearLessThanEqualToMaxYear = date.getYear() <= MAX_YEAR;

        checkArgument(isYearLessThanEqualToMaxYear, MESSAGE_FOR_YEAR_TOO_LARGE);
        checkArgument(isYearMoreThanEqualToMinYear, MESSAGE_FOR_YEAR_TOO_SMALL);
        return null;
    }
}
