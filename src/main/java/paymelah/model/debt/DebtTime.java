package paymelah.model.debt;

import static java.time.temporal.ChronoUnit.MINUTES;
import static java.util.Objects.requireNonNull;
import static paymelah.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Debt's time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class DebtTime implements Comparable<DebtTime> {
    public static final String DEFAULT_TIME = "00:00";
    public static final String MESSAGE_CONSTRAINTS =
            "Time should be a valid time in hh:mm format; where h is hour in 24h clock and m is minute.";
    public static final DateTimeFormatter TIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("H:m");
    public static final DateTimeFormatter TIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    private final LocalTime time;

    /**
     * Constructs a {@code DebtTime}.
     *
     * @param time A valid time.
     */
    public DebtTime(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        this.time = LocalTime.parse(time, TIME_INPUT_FORMAT);
    }

    /**
     * Constructs a {@code DebtTime} with the current time.
     */
    public DebtTime() {
        this.time = LocalTime.now().truncatedTo(MINUTES);
    }

    /**
     * Returns true if a given string is a valid time in the correct format.
     *
     * @param test The string to test for validity.
     * @return true if the given string is a valid time in the correct format.
     */
    public static boolean isValidTime(String test) {
        try {
            LocalTime.parse(test, TIME_INPUT_FORMAT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return time.format(TIME_OUTPUT_FORMAT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DebtTime // instanceof handles nulls
                && time.equals(((DebtTime) other).time)); // state check
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }

    @Override
    public int compareTo(DebtTime o) {
        return this.time.compareTo(o.time);
    }
}
