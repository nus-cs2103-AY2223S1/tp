package gim.model.tag;

import static gim.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Date of an Exercise in Gim.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDate(String)}
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS = "Date should be in DD/MM/YYYY format (e.g. 15/05/2002)";
    // Input string format for the date should be DD/MM/YYYY (e.g. 25/01/2022)
    public static final String VALIDATION_REGEX = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";

    public final LocalDate date;
    public final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Constructs a {@code Date}.
     *
     * @param date A date with valid format.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date, formatter);
    }

    /**
     * Constructs a Date object that is initialized to today's date.
     *
     * This is used when the user did not input any date.
     */
    public Date() {
        this.date = LocalDate.parse(LocalDate.now().toString(), formatter);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && date.equals(((Date) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}
