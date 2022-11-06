package seedu.application.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.util.AppUtil.checkArgument;

/**
 * Represents a Company's internship position in the application list.
 * Guarantees: immutable; is valid as declared in {@link #isValidPosition(String)}
 */
public class Position implements Comparable<Position> {

    public static final String MESSAGE_CONSTRAINTS = "Positions should only contain alphanumeric characters and "
            + "spaces, and it should not be blank";

    /*
     * The first character of the position applied must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs an {@code position}.
     *
     * @param position A valid position applied.
     */
    public Position(String position) {
        requireNonNull(position);
        checkArgument(isValidPosition(position), MESSAGE_CONSTRAINTS);
        value = position;
    }

    /**
     * Returns true if a given string is a valid position.
     */
    public static boolean isValidPosition(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Position // instanceof handles nulls
                && value.toLowerCase().equals(((Position) other).value.toLowerCase())); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Position other) {
        return value.toLowerCase().compareTo(other.value.toLowerCase());
    }
}
