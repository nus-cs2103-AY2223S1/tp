package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.commons.util.AppUtil.checkArgument;
import static seedu.foodrem.model.item.ItemBoughtDate.BOUGHT_DATE_FORMATTER;
import static seedu.foodrem.model.item.ItemBoughtDate.BOUGHT_DATE_PATTERN_REGEX;

import java.time.LocalDate;

import seedu.foodrem.commons.util.ValidationUtil;

/**
 * Validation class for item dates.
 */
public class ItemBoughtDateValidator implements Validator {

    // Validation for parsing
    private static final String MESSAGE_FOR_UNABLE_TO_PARSE_BOUGHT_DATE =
            "The item bought date must follow the format dd-mm-yyyy.";

    // Validation for year
    private static final int MIN_YEAR = 1900;
    private static final String MESSAGE_FOR_YEAR_TOO_SMALL =
            String.format("The year for item bought date should be larger than %d.", MIN_YEAR);

    private static final int MAX_YEAR = 2300;
    private static final String MESSAGE_FOR_YEAR_TOO_LARGE =
            String.format("The year for item bought date should be less than %d.", MAX_YEAR);

    /**
     * Validates a given input String.
     *
     * @param dateString String representation of date to validate against.
     */
    public static Void validate(String dateString) {
        boolean isDateStringParsable = ValidationUtil.isParsableDateString(dateString, BOUGHT_DATE_PATTERN_REGEX);
        checkArgument(isDateStringParsable, MESSAGE_FOR_UNABLE_TO_PARSE_BOUGHT_DATE);

        LocalDate date = LocalDate.parse(dateString, BOUGHT_DATE_FORMATTER);

        boolean isYearLessThanEqualToMaxYear = date.getYear() <= MAX_YEAR;
        boolean isYearMoreThanEqualToMinYear = date.getYear() >= MIN_YEAR;

        checkArgument(isYearLessThanEqualToMaxYear, MESSAGE_FOR_YEAR_TOO_LARGE);
        checkArgument(isYearMoreThanEqualToMinYear, MESSAGE_FOR_YEAR_TOO_SMALL);
        return null;
    }
}
