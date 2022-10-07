package seedu.address.model.item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Validates if a date is parsable according to the dateFormatter provided.
 * Provided an isValidDateString that returns a boolean value indicating if it is parsable.
 */
public class DateValidator {
    private final DateTimeFormatter dateTimeFormatter;
    private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructor for a DateValidator object.
     *
     * @param dateFormatter the format in which we will validate dateStrings according to.
     */
    public DateValidator(DateTimeFormatter dateFormatter) {
        dateTimeFormatter = Optional.of(dateFormatter).orElse(DEFAULT_DATE_TIME_FORMATTER);
    }

    /**
     * Returns true if a provided dateString can be parsed according to {@link DateValidator#dateTimeFormatter}.
     * Returns false otherwise.
     *
     * @param dateString a string to be checked if it is parsable according to the
     *                   {@link DateValidator#dateTimeFormatter}.
     * @return true if the dateString is parsable, false otherwise.
     */
    public boolean isParsableDateString(String dateString) {
        try {
            LocalDate.parse(dateString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
