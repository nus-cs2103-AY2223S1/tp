package seedu.taassist.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.AppUtil.checkArgument;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.taassist.commons.util.StringUtil.caseInsensitiveEquals;

import java.time.LocalDate;

import seedu.taassist.model.uniquelist.Identity;

/**
 * Represents a Session for a {@code ModuleClass} in TA-Assist.
 * Guarantees: immutable; name is valid as declared in {@link #isValidSessionName(String)}
 */
public class Session implements Identity<Session>, Comparable<Session> {
    public static final String MESSAGE_CONSTRAINTS = "Session names should contain only letters, digits, whitespace "
            + "or underscores and should not be empty.";

    /*
     * The first character of the session must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[\\w\\s]+$";

    private final String sessionName;
    private final Date date;

    /**
     * Constructs a {@code Session} with its date set to epoch time.
     *
     * @param sessionName A valid session name.
     */
    public Session(String sessionName) {
        requireNonNull(sessionName);
        checkArgument(isValidSessionName(sessionName), MESSAGE_CONSTRAINTS);
        this.sessionName = sessionName;
        this.date = new Date(LocalDate.now());
    }

    /**
     * Constructs a {@code Session} with the provided {@code sessionName} and {@code date}.
     *
     * @param sessionName A valid session name.
     * @param date {@code Date}.
     */
    public Session(String sessionName, Date date) {
        requireAllNonNull(sessionName, date);
        checkArgument(isValidSessionName(sessionName), MESSAGE_CONSTRAINTS);
        this.sessionName = sessionName;
        this.date = date;
    }

    /**
     * Returns true if a given string is a valid session name.
     */
    public static boolean isValidSessionName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getSessionName() {
        return sessionName;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Session // instanceof handles nulls
                && caseInsensitiveEquals(sessionName, ((Session) other).sessionName) // state check
                && date.equals(((Session) other).date));
    }

    /**
     * Returns true if both sessions have the same name.
     * This defines a weaker notion of equality between two sessions.
     *
     * @param otherSession the session to be compared to.
     * @return true if both sessions have the same name.
     */
    @Override
    public boolean isSame(Session otherSession) {
        return otherSession == this
                || (otherSession != null && caseInsensitiveEquals(sessionName, otherSession.sessionName));
    }

    @Override
    public int hashCode() {
        return sessionName.hashCode();
    }

    /**
     * Formats state as text for viewing.
     */
    public String toString() {
        return String.format("[%s; Date: %s]", getSessionName(), getDate());
    }

    @Override
    public int compareTo(Session other) {
        if (!date.equals(other.date)) {
            return other.date.compareTo(date);
        } else {
            return sessionName.compareTo(other.sessionName);
        }
    }
}
