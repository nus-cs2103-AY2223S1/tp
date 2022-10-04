package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's religion in the application.
 * Guarantees: immutable; is valid as declared in {@link #isValidReligion(String)}
 */
public class Religion {

    public static final String MESSAGE_CONSTRAINTS =
            "Religion should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String religion;

    /**
     * Constructs a {@code Religion}.
     *
     * @param religion A valid religion.
     */
    public Religion(String religion) {
        requireNonNull(religion);
        checkArgument(isValidReligion(religion), MESSAGE_CONSTRAINTS);
        this.religion = religion;
    }

    /**
     * Returns true if a given string is a valid religion.
     */
    public static boolean isValidReligion(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return religion;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Religion // instanceof handles nulls
                && religion.equals(((Religion) other).religion)); // state check
    }

    @Override
    public int hashCode() {
        return religion.hashCode();
    }
}
