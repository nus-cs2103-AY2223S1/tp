package seedu.address.model.entry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Entry's date in the penny wise application.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {
    public static final String MESSAGE_CONSTRAINTS =
            "Dates should be of the format dd-mm-yyyy and it should only contain numbers";
    public static final String VALIDATION_PATTERN = "dd-MM-yyyy";
    public static final String VALIDATION_REGEX =
            "^([0-2][0-9]||3[0-1])-(0[1-9]||[1-9]||1[0-2])-([0-9][0-9])?[0-9][0-9]$";

    public final LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern(VALIDATION_PATTERN));
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the year-month of the date.
     *
     * @return Year-Month of the date.
     */
    public Month getYearMonth() {
        return new Month(date);
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern(VALIDATION_PATTERN));
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
}

