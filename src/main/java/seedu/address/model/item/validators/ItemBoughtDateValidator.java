package seedu.address.model.item.validators;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.address.model.item.Item;
import seedu.address.model.validator.DateValidator;

/**
 * Validation class for item dates.
 */
public class ItemBoughtDateValidator {

    // Validation for parsing
    /**
     * Java 8 uses 'uuuu' for year, not 'yyyy'. In Java 8, ‘yyyy’ means “year of era” (BC or AD).
     */
    private static final String DATE_INPUT_PATTERN_REGEX = "uuuu-MM-dd";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_INPUT_PATTERN_REGEX);
    private static final String MESSAGE_FOR_UNABLE_TO_PARSE =
            String.format("Dates must follow the format %s.", DATE_INPUT_PATTERN_REGEX);

    // Validation for year
    private static final int MIN_YEAR = 1900;
    private static final String MESSAGE_FOR_YEAR_TOO_SMALL =
            String.format("Year should be larger than %d.", MIN_YEAR);

    private static final int MAX_YEAR = 2300;
    private static final String MESSAGE_FOR_YEAR_TOO_LARGE =
            String.format("Year should be less than %d.", MAX_YEAR);

    /**
     * Validates a given input String.
     *
     * @param dateString String representation of date to validate against.
     */
    public static void validate(String dateString) {
        checkArgument(isParsableItemDatetime(dateString), MESSAGE_FOR_UNABLE_TO_PARSE);
        LocalDate date = LocalDate.parse(dateString);
        checkArgument(isYearMoreThanMaxYear(date), MESSAGE_FOR_YEAR_TOO_LARGE);
        checkArgument(isYearLessThanMinYear(date), MESSAGE_FOR_YEAR_TOO_SMALL);
    }

    /**
     * Returns true if an item date is parsable, false otherwise.
     *
     * @param dateTimeString a string that represents the itemDate of the format
     *                       {@link ItemBoughtDateValidator#DATE_INPUT_PATTERN_REGEX}
     */
    public static boolean isParsableItemDatetime(String dateTimeString) {
        DateValidator validator = new DateValidator(DATE_TIME_FORMATTER);
        return validator.isParsableDateString(dateTimeString);
    }

    /**
     * Returns true if an item date has a year more than {@link ItemBoughtDateValidator#MAX_YEAR}, false otherwise.
     *
     * @param date a local date that represents the date of the {@link Item}.
     */
    public static boolean isYearMoreThanMaxYear(LocalDate date) {
        return date.getYear() > MAX_YEAR;
    }

    /**
     * Returns true if an item date has a year less than {@link ItemBoughtDateValidator#MIN_YEAR}, false otherwise.
     *
     * @param date a LocalDate that represents the date of the {@link Item}.
     */
    public static boolean isYearLessThanMinYear(LocalDate date) {
        return date.getYear() < MIN_YEAR;
    }
}
