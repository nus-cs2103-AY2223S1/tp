package seedu.address.model.item.itemvalidator;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.address.model.item.Item;
import seedu.address.model.validator.DateValidator;

/**
 * Validation class for item dates.
 */
public class ItemDateValidator {

    // Validation for parsing
    private static final String DATE_INPUT_PATTERN_REGEX = "yyyy-MM-dd";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_INPUT_PATTERN_REGEX);
    private static final String MESSAGE_FOR_UNABLE_TO_PARSE =
            String.format("Dates must follow the format %s.", DATE_INPUT_PATTERN_REGEX);

    // Validation for year
    private static final int MIN_YEAR = 1000;
    private static final String MESSAGE_FOR_YEAR_TOO_SMALL =
            String.format("Year should be larger than %d.", MIN_YEAR);

    private static final int MAX_YEAR = 2100;
    private static final String MESSAGE_FOR_YEAR_TOO_LARGE =
            String.format("Year should be less than %d.", MAX_YEAR);

    /**
     * Validates a given input String.
     * @param dateString String representation of date to validate against.
     */
    public static void validate(String dateString) {
        checkArgument(isParsableItemDatetime(dateString), MESSAGE_FOR_UNABLE_TO_PARSE);
        LocalDate date = LocalDate.parse(dateString);
        checkArgument(isYearMoreThanMaxYear(date), MESSAGE_FOR_YEAR_TOO_LARGE);
        checkArgument(isYearLessThanMaxYear(date), MESSAGE_FOR_YEAR_TOO_SMALL);
    }

    /**
     * Returns true if an item date is parsable, false otherwise.
     *
     * @param dateTimeString a string that represents the itemDate of the format
     *                       {@link ItemDateValidator#DATE_INPUT_PATTERN_REGEX}
     */
    private static boolean isParsableItemDatetime(String dateTimeString) {
        DateValidator validator = new DateValidator(DATE_TIME_FORMATTER);
        return validator.isParsableDateString(dateTimeString);
    }

    /**
     * Returns true if an item date has a year more than {@link ItemDateValidator#MAX_YEAR}, false otherwise.
     *
     * @param date a local date that represents the date of the {@link Item}.
     */
    private static boolean isYearMoreThanMaxYear(LocalDate date) {
        return date.getYear() > MAX_YEAR;
    }

    /**
     * Returns true if an item date has a year less than {@link ItemDateValidator#MIN_YEAR}, false otherwise.
     *
     * @param date a LocalDate that represents the date of the {@link Item}.
     */
    private static boolean isYearLessThanMaxYear(LocalDate date) {
        return date.getYear() < MIN_YEAR;
    }
}
