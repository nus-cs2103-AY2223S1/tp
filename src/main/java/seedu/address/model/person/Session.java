package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;


/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Session implements Comparable<Session> {

    public static final String MESSAGE_CONSTRAINTS =
            "Session should only be in the format of DAY HH:mm";
    public static final String VALIDATION_REGEX_DAY = "^(\\w){3}$";
    public static final String VALIDATION_REGEX_TIME = "^(\\d){2}:(\\d){2}$";
    protected static final DateTimeFormatter DTF = new DateTimeFormatterBuilder()
            .appendPattern("EEE HH:mm").parseDefaulting(ChronoField.YEAR, 2000)
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1) //defaulting year to workaround class issues.
            .parseDefaulting(ChronoField.ALIGNED_WEEK_OF_MONTH, 1) //defaulting week to workaround class issues.
            .toFormatter();
    public final String session;
    public final LocalDateTime time;

    /**
     * Constructs an {@code Session}.
     *
     * @param session a description of the session.
     */
    public Session(String session) {
        requireNonNull(session);
        checkArgument(isValidSession(session), MESSAGE_CONSTRAINTS);
        this.session = session;
        this.time = LocalDateTime.parse(session, DTF);
    }

    /**
     * Returns true if a given string is a valid session.
     */
    public static boolean isValidSession(String test) {
        if (test.length() != 9) {
            return false;
        }
        String day = test.substring(0, 3);
        String hour = test.substring(4);
        return day.matches(VALIDATION_REGEX_DAY) && hour.matches(VALIDATION_REGEX_TIME) && isValidDay(day);
    }

    /**
     * Returns true if a given string is a valid day.
     */
    private static boolean isValidDay(String test) {
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        for (String day : days) {
            if (test.equalsIgnoreCase(day)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.session;
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
        return this.time.compareTo(other.time);
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }
}
