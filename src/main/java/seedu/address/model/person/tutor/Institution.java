package seedu.address.model.person.tutor;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the institution that the tutor has graduated from.
 */
public class Institution {

    public static final String MESSAGE_CONSTRAINTS =
            "Institution name should only contain alphanumeric characters and spaces, and should not be left blank.\n";

    /*
     * The first character of the institution names must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String institution;

    /**
     * Constructs a {@code Institution}.
     *
     * @param institution A valid institution name.
     */
    public Institution(String institution) {
        requireNonNull(institution);
        checkArgument(isValidInstitution(institution), MESSAGE_CONSTRAINTS);
        this.institution = institution;
    }

    /**
     * Returns true if a given string is a valid institution name.
     */
    public static boolean isValidInstitution(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return institution;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Institution // instanceof handles nulls
                && institution.equals(((Institution) other).institution)); // state check
    }

    @Override
    public int hashCode() {
        return institution.hashCode();
    }
}
