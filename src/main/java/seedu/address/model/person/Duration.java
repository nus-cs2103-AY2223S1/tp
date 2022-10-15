package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Duration implements Comparable<Duration> {

    public static final String MESSAGE_CONSTRAINTS =
            "Duration should only be in the format of HH:mm to HH:mm";

    public static final String VALIDATION_REGEX = "^(\\d){2}:(\\d){2}$";

    protected static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("HH:mm");
    public final LocalTime start;
    public final LocalTime end;

    /**
     * Constructs an {@code Duration}.
     *
     * @param duration a description of the duration.
     */
    public Duration(String duration) {
        requireNonNull(duration);
        checkArgument(isValidDuration(duration), MESSAGE_CONSTRAINTS);
        this.start = LocalTime.parse(duration.substring(0, 5), DTF);
        this.end = LocalTime.parse(duration.substring(6), DTF);
    }

    /**
     * Returns true if a given string is a valid duration.
     */
    public static boolean isValidDuration(String test) {
        if (test.length() < 11) {
            return false;
        }
        String start = test.substring(0, 5);
        String end = test.substring(6);
        return start.matches(VALIDATION_REGEX) && end.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        String duration = start.format(DTF) + "-" + end.format(DTF);
        return duration;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { //short circuit if same object
            return true;
        } else if (!(other instanceof Duration)) { //instanceof handles nulls
            return false;
        }
        Duration temp = (Duration) other;
        return temp.toString().equalsIgnoreCase(this.toString());
    }


    @Override
    public int compareTo(Duration other) {
        return this.start.compareTo(other.start);
    }

    @Override
    public int hashCode() {
        return start.hashCode() * end.hashCode();
    }
}
