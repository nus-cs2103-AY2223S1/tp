package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's location in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidUsername(String)} (String)}
 */
public class GithubUsername {

    public static final String MESSAGE_CONSTRAINTS = "Github Username can take any values, and it should not be blank";

    public static final String DEFAULT_USERNAME = "/";

    /*
     * The first character of the github username must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a Location.
     *
     * @param username A valid github username.
     */
    public GithubUsername(String username) {
        requireNonNull(username);
        checkArgument(isValidUsername(username), MESSAGE_CONSTRAINTS);
        value = username;
    }

    /**
     * Returns true if a given string is a valid username.
     */
    public static boolean isValidUsername(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GithubUsername // instanceof handles nulls
                && value.equals(((GithubUsername) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
