package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the date of an internship in the list.
 */
public class Date extends ComparableModel {
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in dd-mm-yyyy format";

    public static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public final LocalDate value;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date in string format of dd-mm-yyyy.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.value = LocalDate.parse(date, DEFAULT_FORMATTER);
    }

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(LocalDate date) {
        this.value = date;
    }

    /**
     * Returns true if a given string is in a valid date format.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, DEFAULT_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns a string representation of the date in input format.
     */
    public String toInputFormat() {
        return value.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Returns a string representation of the date in display format.
     */
    public String toDisplayFormat() {
        return DISPLAY_FORMATTER.format(value);
    }

    @Override
    public int compareTo(ComparableModel other) {
        if (other instanceof Date) {
            return this.value.compareTo(((Date) other).value);
        }
        return 0;
    }

    @Override
    public String toString() {
        return value.toString();
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


}
