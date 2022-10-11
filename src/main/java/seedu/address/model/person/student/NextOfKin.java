package seedu.address.model.person.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the next of kin of a student.
 */
public class NextOfKin {

    public static final String MESSAGE_CONSTRAINTS =
            "Next of kin names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the next of kin names must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String nextOfKin;

    /**
     * Constructs a {@code NextOfKin}.
     *
     * @param nextOfKin A valid next of kin name.
     */
    public NextOfKin(String nextOfKin) {
        requireNonNull(nextOfKin);
        checkArgument(isValidNextOfKin(nextOfKin), MESSAGE_CONSTRAINTS);
        this.nextOfKin = nextOfKin;
    }

    /**
     * Returns true if a given string is a valid next of kin name.
     */
    public static boolean isValidNextOfKin(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return nextOfKin;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NextOfKin // instanceof handles nulls
                && nextOfKin.equals(((NextOfKin) other).nextOfKin)); // state check
    }

    @Override
    public int hashCode() {
        return nextOfKin.hashCode();
    }
}
