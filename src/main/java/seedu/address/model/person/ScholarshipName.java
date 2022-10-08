package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Applicant's Scholarship name in TrackAScholar.
 * Guarantees: immutable; is valid as declared in {@link #isValidScholarshipName(String)}
 */
public class ScholarshipName {

    public static final String MESSAGE_CONSTRAINTS = "Scholarship can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public ScholarshipName(String address) {
        requireNonNull(address);
        checkArgument(isValidScholarshipName(address), MESSAGE_CONSTRAINTS);
        value = address;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidScholarshipName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScholarshipName // instanceof handles nulls
                && value.equals(((ScholarshipName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
