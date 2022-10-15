package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Session implements Comparable<Session> {

    public static final String MESSAGE_CONSTRAINTS =
            "Session should only be in the format of HH:mm to HH:mm";

    public static final String VALIDATION_REGEX = "^(\\d){2}:(\\d){2}$";

    protected static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("HH:mm");
    public final LocalTime start;
    public final LocalTime end;

    /**
     * Constructs an {@code Session}.
     *
     * @param session a description of the session.
     */
    public Session(String session) {
        requireNonNull(session);
        checkArgument(isValidSession(session), MESSAGE_CONSTRAINTS);
        this.start = LocalTime.parse(session.substring(0, 5), DTF);
        this.end = LocalTime.parse(session.substring(6), DTF);
    }

    /**
     * Returns true if a given string is a valid session.
     */
    public static boolean isValidSession(String test) {
        if (test.length() < 11) {
            return false;
        }
        String start = test.substring(0, 5);
        String end = test.substring(6);
        return start.matches(VALIDATION_REGEX) && end.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        String session = start.format(DTF) + "-" + end.format(DTF);
        return session;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { //short circuit if same object
            return true;
        } else if (!(other instanceof Session)) { //instanceof handles nulls
            return false;
        }
        Session temp = (Session) other;
        return temp.toString().equalsIgnoreCase(this.toString());
    }


    @Override
    public int compareTo(Session other) {
        return this.start.compareTo(other.start);
    }

    @Override
    public int hashCode() {
        return start.hashCode() * end.hashCode();
    }
}
