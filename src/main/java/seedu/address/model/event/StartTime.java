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
    private static final String MESSAGE_ARGUMENT_CONSTRAINTS =
            "compareTo() of StartTime must take in argument of type LocalTime";

    //for checking if valid input date format and for changing to storage friendly format
    private static final DateTimeFormatter checkAndLogFormatter = DateTimeFormatter
            .ofPattern("[hh/mm/a][h/mm/a][hh/m/a][h/m/a]");

    //for changing to user-readable format
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a");

    public final LocalTime startTime;

    private boolean isEmpty;

    /**
     * Constructs an empty {@code StartTime}.
     */
    private StartTime() {
        this.startTime = null;
        this.isEmpty = true;
    }

    /**
     * Constructs a {@code StartTime}.
     *
     * @param startTime A valid start time.
     */
    public StartTime(String startTime) {
        requireNonNull(startTime);
        checkArgument(isValidStartTime(startTime), MESSAGE_CONSTRAINTS);
        this.startTime = LocalTime.parse(startTime, checkAndLogFormatter);
        this.isEmpty = false;
    }

    /**
     * Constructs an empty {@code StartTime}.
     */
    public static StartTime getEmptyStartTime() {
        return new StartTime();
    }

    /**
     * Returns true if a given string is a valid StartTime input.
     * "" empty string is used to represent an empty StartTime.
     * @return boolean
     */

    //found from https://mkyong.com/java/how-to-check-if-date-is-valid-in-java/
    public static boolean isValidStartTime(String test) {
        if (test == null) {
            return true;
        }
        try {
            LocalTime.parse(test, checkAndLogFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("StartTime problem");
            return false;
        }
        return true;
    }

    /**
     * Returns 1 if the other object is a StartTime that is later,
     * -1 if the other object is a StartTime that is earlier,
     * and 0 if the other object is a StartTime that is of the same time.
     * @param other The object to compare with
     * @return int
     */
    public int compareTo(Object other) {
        if (other == null) {
            return -1;
        }
        if (!(other instanceof StartTime)) {
            throw new IllegalArgumentException(MESSAGE_ARGUMENT_CONSTRAINTS);
        }
        if (this.isEmpty() & ((StartTime) other).isEmpty()) {
            return 0;
        }
        return this.startTime.compareTo(((StartTime) other).startTime);
    }

    /**
     * Returns true if StartTime is empty, false otherwise
     * @return boolean
     */
    public boolean isEmpty() {
        return this.isEmpty;
    }

    /**
     * Returns the String representation of the StartTime in format suitable for storage logging
     * @return String
     */
    public String toLogFormat() {
        if (this.isEmpty()) {
            return null;
        }
        return this.startTime.format(checkAndLogFormatter);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof StartTime)) {
            throw new IllegalArgumentException(MESSAGE_ARGUMENT_CONSTRAINTS);
        }
        if (this.isEmpty() & ((StartTime) other).isEmpty()) {
            return true;
        }
        return this.startTime.equals(((StartTime) other).startTime);
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "";
        }
        return this.startTime.format(outputFormatter);
    }

    @Override
    public int hashCode() {
        return this.startTime.hashCode();
    }
}
