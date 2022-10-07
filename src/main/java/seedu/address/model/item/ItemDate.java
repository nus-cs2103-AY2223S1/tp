package seedu.address.model.item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an item date in an {@link Item}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemDate {

    public final LocalDate itemDate;

    private static final String DATE_OUTPUT_PATTERN_REGEX = "dd/MM/yyyy";

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
     * Constructs an itemDate.
     *
     * @param dateString a string that represents the itemDate of the format
     *                   {@link ItemDate#DATE_INPUT_PATTERN_REGEX}
     */
    public ItemDate(String dateString) {
        requireNonNull(dateString);

        checkArgument(isParsableItemDatetime(dateString), MESSAGE_FOR_UNABLE_TO_PARSE);

        LocalDate date = LocalDate.parse(dateString);
        checkArgument(isYearMoreThanMaxYear(date), MESSAGE_FOR_YEAR_TOO_LARGE);
        checkArgument(isYearLessThanMaxYear(date), MESSAGE_FOR_YEAR_TOO_SMALL);

        itemDate = date;
    }

    /**
     * Returns true if an item date is parsable, false otherwise.
     *
     * @param dateTimeString a string that represents the itemDate of the format
     *                       {@link ItemDate#DATE_INPUT_PATTERN_REGEX}
     */
    private static boolean isParsableItemDatetime(String dateTimeString) {
        DateValidator validator = new DateValidator(DATE_TIME_FORMATTER);
        return validator.isParsableDateString(dateTimeString);
    }

    /**
     * Returns true if an item date has a year more than {@link ItemDate#MAX_YEAR}, false otherwise.
     *
     * @param date a local date that represents the {@link ItemDate#itemDate}.
     */
    private static boolean isYearMoreThanMaxYear(LocalDate date) {
        return date.getYear() > MAX_YEAR;
    }

    /**
     * Returns true if an item date has a year less than {@link ItemDate#MIN_YEAR}, false otherwise.
     *
     * @param date a LocalDate that represents the {@link ItemDate#itemDate}.
     */
    private static boolean isYearLessThanMaxYear(LocalDate date) {
        return date.getYear() < MIN_YEAR;
    }

    /**
     * Returns a string representation of {@link ItemDate}.
     *
     * @param localDate a LocalDate that represents the {@link ItemDate#itemDate}.
     * @return a string representation of {@link ItemDate#itemDate}.
     */
    public static String getDatetimeStringFromLocalDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(DATE_OUTPUT_PATTERN_REGEX));
    }

    /**
     * Returns true if both {@link ItemDate#itemDate} have the same date by
     * {@link LocalDate#equals(Object)}.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemDate // instanceof handles nulls
                && itemDate.equals(((ItemDate) other).itemDate)); // state check
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return itemDate.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getDatetimeStringFromLocalDate(itemDate);
    }
}
