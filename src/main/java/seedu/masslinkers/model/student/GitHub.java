package seedu.masslinkers.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.masslinkers.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's GitHub in the mass linkers.
 * Guarantees: immutable; is valid as declared in {@link #isValidGitHub(String)}
 */
public class GitHub {

    // GitHub username constraints adapted from https://github.com/shinnn/github-username-regex
    public static final String MESSAGE_CONSTRAINTS = "Github username is invalid!\n"
                    + "• Github username must be between 1 to 39 characters.\n"
                    + "• Github username may only contain alphanumeric characters or hyphens.\n"
                    + "• Github username cannot begin with hyphens and "
                            + "cannot have multiple consecutive hyphens.";

    // Regex adapted from: https://github.com/shinnn/github-username-regex
    public static final String VALIDATION_REGEX = "^[a-z\\d](?:[a-z\\d]-?){0,38}$";

    public final String username;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public GitHub(String name) {
        requireNonNull(name);
        checkArgument(isValidGitHub(name), MESSAGE_CONSTRAINTS);
        username = name.toLowerCase();
    }

    /**
     * Returns true if a given string is a valid GitHub username.
     */
    public static boolean isValidGitHub(String test) {
        return test.toLowerCase().matches(VALIDATION_REGEX);
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
