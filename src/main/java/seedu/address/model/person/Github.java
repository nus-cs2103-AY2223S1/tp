package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's GitHub profile in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidUsername(String)}
 */
public class Github {

    public static final String MESSAGE_CONSTRAINTS = "Names should only contain alphanumeric characters and at most "
            + "one hyphen, it should also not begin or end with a hyphen and it should not be blank and should have "
            + "maximum 39 characters";

    /*
     * The first and last character of the username must not be a hyphen,
     * maximum 39 characters
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9]([-](?![-])|[a-zA-Z0-9]){0,37}[a-zA-Z0-9]$";

    public final String value;

    /**
     * Constructs a {@code Github}.
     *
     * @param username A valid username.
     */
    public Github(String username) {
        requireNonNull(username);
        checkArgument(isValidUsername(username), MESSAGE_CONSTRAINTS);
        this.value = username;
    }

    /**
     * Returns true if a given string is a valid username.
     */
    public static boolean isValidUsername(String test) {
        if (test.equals("")) {
            return true;
        }
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
