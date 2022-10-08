package seedu.taassist.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.AppUtil.checkArgument;

import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Represents a Session for a {@code ModuleClass} in TA-Assist.
 * Guarantees: immutable;
 */
public class Session {
    public static final String MESSAGE_CONSTRAINTS = "Session names can take any values, but it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String sessionName;

    /**
     * Constructs a {@code Session}.
     *
     * @param sessionName A valid class name.
     */
    public Session(String sessionName) {
        requireNonNull(sessionName);
        checkArgument(isValidSessionName(sessionName), MESSAGE_CONSTRAINTS);
        this.sessionName = sessionName;
    }

    /**
     * Returns true if a given string is a valid session name.
     */
    public static boolean isValidSessionName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Session // instanceof handles nulls
                && sessionName.equals(((Session) other).sessionName)); // state check
    }

    @Override
    public int hashCode() {
        return sessionName.hashCode();
    }

    /**
     * Formats state as text for viewing.
     */
    public String toString() {
        return sessionName;
    }
}
