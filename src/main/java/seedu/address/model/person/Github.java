package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's GitHub profile in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGh(String)}
 */
public class Github {

    public static final String MESSAGE_CONSTRAINTS = "Github profiles can take any values "
            + "(whether name or link to Github profile page, and it should not be blank)";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code Github}.
     *
     * @param gh A valid GitHub profile.
     */
    public Github(String gh) {
        requireNonNull(gh);
        checkArgument(isValidGh(gh), MESSAGE_CONSTRAINTS);
        value = gh;
    }

    /**
     * Returns true if a given string is a valid GitHub profile type (username or website link).
     */
    public static boolean isValidGh(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Github // instanceof handles nulls
                && value.equals(((Github) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

