package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's year in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidYear(String)} (String)}
 */
public class Year {

    public static final String MESSAGE_CONSTRAINTS =
            "Year can only take integer values from 1 to 4";

    public static final String EMPTY_YEAR = "";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[1-4]";

    public final String value;

    /**
     * Constructs a {@code Year}.
     *
     * @param year A valid year.
     */
    public Year(String year) {
        requireNonNull(year);
        checkArgument(isValidYear(year), MESSAGE_CONSTRAINTS);
        value = year;
    }

    /**
     * @param year A valid year.
     * @param isPresent Whether prefix was present in user input.
     */
    public Year(String year, boolean isPresent) {
        if (isPresent) {
            checkArgument(isValidYear(year), MESSAGE_CONSTRAINTS);
            value = year;
        } else {
            value = EMPTY_YEAR;
        }
    }

    /**
     * Returns true if a given string is a valid rating.
     */
    public static boolean isValidYear(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Year // instanceof handles nulls
                && value.equals(((Year) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
