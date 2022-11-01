package seedu.waddle.model.itinerary;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;

/**
 * Represents an Itinerary's Date.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS =
            "Please provide a valid date in the following format: yyyy-mm-dd.";

    /*
     * The current regex aims to express YYYY-MM-DD, this can be broken with
     * expressions like 9999-00-00.
     * TODO: use another way to validate (can consider using java Datetime class or smth)
     */
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}";

    public final LocalDate date;

    /**
     * Constructs a {@code Name}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public LocalDate getValue() {
        return this.date;
    }

    @Override
    public String toString() {
        return date.toString();
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
