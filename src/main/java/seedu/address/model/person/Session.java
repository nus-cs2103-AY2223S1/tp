package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.List;


/**
 * Represents a Person's session in the address book.
 * Guarantees: immutable; is always valid
 */
public class Session implements Comparable<Session> {

    public static final String MESSAGE_CONSTRAINTS =
            "Session should only be in the format of DDD HH:mm and range from 00:00 to 23:59";
    public static final String VALIDATION_REGEX_DAY = "^(\\w){3}$";
    public static final String VALIDATION_REGEX_TIME = "^(\\d){2}:(\\d){2}$";
    protected static final DateTimeFormatter DTF = new DateTimeFormatterBuilder()
            .appendPattern("EEE HH:mm").parseDefaulting(ChronoField.YEAR, 2000)
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1) //defaulting year to workaround class issues.
            .parseDefaulting(ChronoField.ALIGNED_WEEK_OF_MONTH, 1) //defaulting week to workaround class issues.
            .toFormatter();
    private static final List<String> daysList = Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");
    public final String session;
    public final String day;
    public final LocalDateTime time;

    /**
     * Constructs an {@code Session}.
     *
     * @param session a description of the session.
     */
    public Session(String session) {
        requireNonNull(session);
        checkArgument(isValidSession(session), MESSAGE_CONSTRAINTS);
        session = session.substring(0, 1).toUpperCase() + session.substring(1).toLowerCase();
        this.session = session;
        this.day = session.substring(0, 3);
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
        String hourAndMin = test.substring(4);
        return day.matches(VALIDATION_REGEX_DAY) && hourAndMin.matches(VALIDATION_REGEX_TIME) && isValidDay(day)
                && isValidTime(hourAndMin);
    }

    /**
     * Returns true if a given string is a valid day.
     */
    private static boolean isValidDay(String test) {
        for (String day : daysList) {
            if (test.equalsIgnoreCase(day)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if a given string has valid hours and minutes.
     */
    private static boolean isValidTime(String test) {
        int hour = Integer.parseInt(test.substring(0, 2));
        int min = Integer.parseInt(test.substring(3, 5));
        if (hour >= 0 && hour < 24) {
            return min >= 0 && min < 60;
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
        LocalDateTime thisTime = this.time;
        LocalDateTime otherTime = other.time;
        int dayCompareTo = this.time.getDayOfWeek().compareTo(other.time.getDayOfWeek());
        if (dayCompareTo != 0) {
            return dayCompareTo;
        }
        int hourCompareTo = thisTime.getHour() - otherTime.getHour();
        if (hourCompareTo != 0) {
            return hourCompareTo;
        }
        return thisTime.getMinute() - otherTime.getMinute();
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }
}
