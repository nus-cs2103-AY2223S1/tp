package seedu.address.model.person.tutor;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the highest qualification the tutor has attained.
 */
public class Qualification {

    public static final String MESSAGE_CONSTRAINTS =
            "Qualification should only contain alphanumeric characters, commas and spaces, "
                    + "and should not be left blank.\n";

    /*
     * The first character of the qualification must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ,]*";

    public final String qualification;

    /**
     * Constructs a {@code Qualification}.
     *
     * @param qualification A valid qualification title.
     */
    public Qualification(String qualification) {
        requireNonNull(qualification);
        checkArgument(isValidQualification(qualification), MESSAGE_CONSTRAINTS);
        this.qualification = qualification;
    }

    /**
     * Returns true if a given string is a valid qualification.
     */
    public static boolean isValidQualification(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return qualification;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Qualification // instanceof handles nulls
                && qualification.equals(((Qualification) other).qualification)); // state check
    }

    @Override
    public int hashCode() {
        return qualification.hashCode();
    }
}
