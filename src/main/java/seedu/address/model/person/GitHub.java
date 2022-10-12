package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's GitHub in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGitHub(String)}
 */
public class GitHub {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String username;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public GitHub(String name) {
        requireNonNull(name);
        checkArgument(isValidGitHub(name), MESSAGE_CONSTRAINTS);
        username = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidGitHub(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return username;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GitHub // instanceof handles nulls
                && username.equals(((GitHub) other).username)); // state check
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

}
