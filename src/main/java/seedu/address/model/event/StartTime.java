package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event's starting time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStartTime(String)}
 */
public class StartTime {

    public static final String MESSAGE_CONSTRAINTS = "Start time must be in format: hh/mm/AM or hh/mm/PM";

    //for checking if valid input date format
    private static final DateTimeFormatter checkFormatter = DateTimeFormatter
            .ofPattern("[hh/mm/a][h/mm/a][hh/m/a][h/m/a]");

    //for changing to storage friendly format
    private static final DateTimeFormatter logFormatter = DateTimeFormatter.ofPattern("hh/mm/a");

    //for changing to user-readable format
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a");

    public final LocalTime time;

    /**
     * Constructs a {@code StartTime}.
     *
     * @param startTime A valid start time.
     */
    public StartTime(String startTime) {
        requireNonNull(startTime);
        assert !startTime.isBlank();
        checkArgument(isValidStartTime(startTime), MESSAGE_CONSTRAINTS);
        this.time = LocalTime.parse(startTime, checkFormatter);
    }

    /**
     * Returns true if a given string is a valid StartTime input.
     * @return boolean
     */
    //found from https://mkyong.com/java/how-to-check-if-date-is-valid-in-java/
    public static boolean isValidStartTime(String test) {
        try {
            LocalTime.parse(test, checkFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    //TODO: To be re-implemented by Benjamin for Sort By Date
    ///**
    // * Returns 1 if the other object is a StartTime that is later,
    // * -1 if the other object is a StartTime that is earlier,
    // * and 0 if the other object is a StartTime that is of the same time.
    // * @param other The object to compare with
    // * @return int
    // */
    //public int compareTo(Object other) {
    //    if (other == null) {
    //        return -1;
    //    }
    //    if (!(other instanceof StartTime)) {
    //        throw new IllegalArgumentException(MESSAGE_ARGUMENT_CONSTRAINTS);
    //    }
    //    if (this.isEmpty() & ((StartTime) other).isEmpty()) {
    //        return 0;
    //    }
    //    return this.startTime.compareTo(((StartTime) other).startTime);
    //}

    /**
     * Returns the String representation of the StartTime in format suitable for storage logging
     * @return String
     */
    public String toLogFormat() {
        return this.time.format(logFormatter);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof StartTime)) {
            return false;
        }

        StartTime st = (StartTime) other;
        return this.time.equals(st.time);
    }

    @Override
    public String toString() {
        return this.time.format(outputFormatter);
    }

    @Override
    public int hashCode() {
        return this.time.hashCode();
    }
}
