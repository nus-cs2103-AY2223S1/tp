package seedu.studmap.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.commons.util.AppUtil.checkArgument;

public class GitName {

    public static final String MESSAGE_CONSTRAINTS = "Github username can be anything";

    public static final String VALIDATION_REGEX = "[\\p{all}]*";

    public final String value;

    public GitName(String username) {
        requireNonNull(username);
        checkArgument(isValidGitName(username), MESSAGE_CONSTRAINTS);
        this.value = username;
    }

    public static boolean isValidGitName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value;
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
