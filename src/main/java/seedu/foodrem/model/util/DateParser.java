package seedu.foodrem.model.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Validates if a date is parsable according to the dateFormatter provided.
 * Provided an isValidDateString that returns a boolean value indicating
 * if it is parsable.
 */
public class DateParser {
    private final DateTimeFormatter dateTimeFormatter;

    /**
     * Constructor for a DateValidator object.
     *
     * @param dateFormatter the format in which we will validate dateStrings according to.
     */
    public DateParser(DateTimeFormatter dateFormatter) {
        assert dateFormatter != null;
        dateTimeFormatter = dateFormatter;
    }

    /**
     * Returns true if a provided dateString can be parsed according to {@link DateParser#dateTimeFormatter}.
     * Returns false otherwise.
     *
     * @param dateString a string to be checked if it is parsable according to the
     *                   {@link DateParser#dateTimeFormatter}.
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
