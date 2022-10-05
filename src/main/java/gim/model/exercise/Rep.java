package gim.model.exercise;

import static gim.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Exercise's Reps in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRep(String)}
 */
public class Rep {

    public static final String MESSAGE_CONSTRAINTS = "Reps can only take Non negative integer values";

    /*
     * The first character of the Rep must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Rep}.
     *
     * @param rep A valid Rep.
     */
    public Rep(String rep) {
        requireNonNull(rep);
        checkArgument(isValidRep(rep), MESSAGE_CONSTRAINTS);
        value = rep;
    }

    /**
     * Returns true if a given string is a valid sets.
     */
    public static boolean isValidRep(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Rep // instanceof handles nulls
                && value.equals(((Rep) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
