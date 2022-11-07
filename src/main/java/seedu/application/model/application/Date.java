package seedu.application.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Represents the Date that the user applied to the Position of the Company.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date implements Comparable<Date> {

    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in the format of yyyy-mm-dd with proper month and leap year support";
    private static final DateTimeFormatter COMMAND_DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter DISPLAY_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public final LocalDate value;

    /**
     * Constructs a {@code Date}.
     *
     * @param dateString A valid date in String.
     */
    public Date(String dateString) {
        requireNonNull(dateString);
        checkArgument(isValidDate(dateString), MESSAGE_CONSTRAINTS);
        value = parseLocalDate(dateString);
    }

    /**
     * Returns true if a given string is a valid date string.
     */
    public static boolean isValidDate(String test) {
        try {
            COMMAND_DATE_FORMATTER.parse(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    boolean isAfter(LocalDate date) {
        return this.value.isAfter(date);
    }

    private LocalDate parseLocalDate(String dateString) {
        return LocalDate.parse(dateString, COMMAND_DATE_FORMATTER);
    }

    /**
     * Returns the command string that corresponds to this {@code Date}.
     *
     * @return The command string that corresponds to this {@code Date}.
     */
    public String toCommandString() {
        return COMMAND_DATE_FORMATTER.format(value);
    }

    @Override
    public String toString() {
        return this.value.format(DISPLAY_DATE_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Date other) {
        return value.compareTo(other.value);
    }
}
