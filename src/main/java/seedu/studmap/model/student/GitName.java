package seedu.studmap.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's git username in the student map.
 * Guarantees: immutable; is valid as declared in {@link #isValidGitName(String)}
 */
public class GitName {

    public static final String NO_GITNAME_STRING = "No GitHub username";

    public static final String GITNAME_DISPLAY_PREFIX = "GitHub: ";

    public static final String MESSAGE_CONSTRAINTS = "Github username can be anything";

    public static final String VALIDATION_REGEX = "[\\p{all}]*";

    public final String value;

    /**
     * Constructs an empty {@code GitName}.
     */
    public GitName() {
        this.value = "";
    }

    /**
     * Constructs a {@code GitName}.
     *
     * @param username A valid name.
     */
    public GitName(String username) {
        requireNonNull(username);
        checkArgument(isValidGitName(username), MESSAGE_CONSTRAINTS);
        this.value = username;
    }

    /**
     * Returns string used for display in UI.
     *
     * @return String to be displayed
     */
    public String getDisplayString() {
        return this.value.isEmpty()
                ? NO_GITNAME_STRING
                : GITNAME_DISPLAY_PREFIX + value;
    }

    /**
     * Returns true if a given string is a valid git username.
     */
    public static boolean isValidGitName(String test) {
        return test.isEmpty() || test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value.isEmpty()
                ? NO_GITNAME_STRING
                : value;
    }

    /**
     * Returns string used for sorting GitName
     */
    public String toCmpString() {
        return this.value.isEmpty()
               ? null
               : value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GitName // instanceof handles nulls
                && value.equals(((GitName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
