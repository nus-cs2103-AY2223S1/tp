package seedu.phu.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.phu.commons.util.AppUtil.checkArgument;

/**
 * Represents the position of the internship.
 */
public class Position extends ComparableModel {
    public static final String MESSAGE_CONSTRAINTS =
            "Position should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the position must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    // TODO modify accordingly to the requirement for position
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String positionName;

    /**
     * Constructs a {@code Position}.
     *
     * @param position A valid position.
     */
    public Position(String position) {
        requireNonNull(position);
        checkArgument(isValidPosition(position), MESSAGE_CONSTRAINTS);
        positionName = position;
    }

    /**
     * Returns true if a given string is a valid position.
     */
    public static boolean isValidPosition(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public int compareTo(ComparableModel other) {
        if (other instanceof Position) {
            return this.toString().compareTo(((Position) other).toString());
        }
        return 0;
    }

    @Override
    public String toString() {
        return positionName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Position // instanceof handles nulls
                && positionName.equals(((Position) other).positionName)); // state check
    }

    @Override
    public int hashCode() {
        return positionName.hashCode();
    }
}
