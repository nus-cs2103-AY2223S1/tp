package tuthub.model.tutor;

import static java.util.Objects.requireNonNull;
import static tuthub.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tutor's year in tuthub.
 * Guarantees: immutable; is valid as declared in {@link #isValidYear(String)}
 */
public class Year {

    public static final String MESSAGE_CONSTRAINTS = "Year should only contain 1 numeric character"
        + " between 1 and 6, and it should not be blank";

    /*
     * Years 1-4 for Undergraduate, year 5 for Masters and year 6 for PhD.
     */
    public static final String VALIDATION_REGEX = "^[1-6]$";

    public final String value;

    /**
     * Constructs an {@code Year}.
     *
     * @param year A valid year.
     */
    public Year(String year) {
        requireNonNull(year);
        checkArgument(isValidYear(year), MESSAGE_CONSTRAINTS);
        value = year;
    }

    /**
     * Returns true if a given string is a valid module code.
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
