package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.commons.util.AppUtil.checkArgument;
import static seedu.foodrem.model.item.ItemBoughtDate.BOUGHT_DATE_FORMATTER;
import static seedu.foodrem.model.item.ItemBoughtDate.BOUGHT_DATE_PATTERN_REGEX;

import java.time.LocalDate;

import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.util.DateParser;

/**
 * Validation class for item dates.
 */
public class ItemBoughtDateValidator implements Validator {

    // Validation for parsing
    public static final String MESSAGE_FOR_UNABLE_TO_PARSE_BOUGHT_DATE =
            String.format("The item bought date must follow the format %s.", BOUGHT_DATE_PATTERN_REGEX);

    // Validation for year
    private static final int MIN_YEAR = 1900;
    private static final String MESSAGE_FOR_YEAR_TOO_SMALL =
            String.format("The year for item bought date should be larger than %d.", MIN_YEAR);

    private static final int MAX_YEAR = 2300;
    private static final String MESSAGE_FOR_YEAR_TOO_LARGE =
            String.format("The year for ite bought date should be less than %d.", MAX_YEAR);

    /**
     * Validates a given input String.
     *
     * @param dateString String representation of date to validate against.
     */
    public static void validate(String dateString) {
        checkArgument(isParsableItemBoughtDate(dateString), MESSAGE_FOR_UNABLE_TO_PARSE_BOUGHT_DATE);
        LocalDate date = LocalDate.parse(dateString, BOUGHT_DATE_FORMATTER);
        checkArgument(isYearLessThanMaxYear(date), MESSAGE_FOR_YEAR_TOO_LARGE);
        checkArgument(isYearMoreThanMinYear(date), MESSAGE_FOR_YEAR_TOO_SMALL);
    }

    /**
     * Returns true if an item date is parsable, false otherwise.
     *
     * @param dateTimeString a string that represents the itemDate of the format provided by formatter.
     */
    private static boolean isParsableItemBoughtDate(String dateTimeString) {
        DateParser validator = new DateParser(BOUGHT_DATE_FORMATTER);
        return validator.isParsableDateString(dateTimeString);
    }

    /**
     * Returns true if an item date has a year more than {@link ItemBoughtDateValidator#MIN_YEAR}, false otherwise.
     *
     * @param date a local date that represents the date of the {@link Item}.
     */
    private static boolean isYearMoreThanMinYear(LocalDate date) {
        return date.getYear() > MIN_YEAR;
    }

    /**
     * Returns true if an item date has a year less than {@link ItemBoughtDateValidator#MAX_YEAR}, false otherwise.
     *
     * @param date a LocalDate that represents the date of the {@link Item}.
     */
    private static boolean isYearLessThanMaxYear(LocalDate date) {
        return date.getYear() < MAX_YEAR;
    }
}
