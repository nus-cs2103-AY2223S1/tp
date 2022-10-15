package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event's starting date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStartDate(String)}
 */
public class StartDate {

    public static final String MESSAGE_CONSTRAINTS = "Start date must be in format: dd/MM/yyyy";
    private static final String MESSAGE_ARGUMENT_CONSTRAINTS =
            "compareTo() of StartDate must take in argument of type LocalDate";

    //for checking if valid input date format
    private static final DateTimeFormatter checkFormatter = DateTimeFormatter
            .ofPattern("[d/M/yyyy][dd/M/yyyy][d/MM/yyyy][dd/MM/yyyy]");

    //for changing to storage friendly format
    private static final DateTimeFormatter logFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    //for changing to user-readable format
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy");

    public final LocalDate date;

    private boolean isEmpty;

    /**
     * Constructs an empty {@code StartDate}.
     */
    private StartDate() {
        this.date = null;
        this.isEmpty = true;
    }

    /**
     * Constructs a {@code StartDate}.
     *
     * @param date A valid date.
     */
    public StartDate(String date) {
        requireNonNull(date);
        checkArgument(isValidStartDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date, logFormatter);
        this.isEmpty = false;
    }

    /**
     * Constructs an empty {@code StartDate}.
     */
    public static StartDate getEmptyStartDate() {
        return new StartDate();
    }

    /**
     * Returns true if a given string is a valid StartDate input.
     * "" empty string is used to represent an empty StartDate.
     * @return boolean
     */

    //found from https://mkyong.com/java/how-to-check-if-date-is-valid-in-java/
    public static boolean isValidStartDate(String test) {
        if (test == null) {
            return true;
        }
        try {
            LocalDate.parse(test, checkFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("StartDate problem");
            return false;
        }
        return true;
    }

    /**
     * Returns 1 if the other object is a StartDate that is later,
     * -1 if the other object is a StartDate that is earlier,
     * and 0 if the other object is a StartDate that is of the same date.
     * @param other The object to compare with
     * @return int
     */
    public int compareTo(Object other) {
        if (other == null) {
            return -1;
        }
        if (!(other instanceof StartDate)) {
            throw new IllegalArgumentException(MESSAGE_ARGUMENT_CONSTRAINTS);
        }
        if (this.isEmpty() & ((StartDate) other).isEmpty()) {
            return 0;
        }
        return this.date.compareTo(((StartDate) other).date);
    }

    /**
     * Returns true if StartDate is empty, false otherwise
     * @return boolean
     */
    public boolean isEmpty() {
        return this.isEmpty;
    }

    /**
     * Returns the String representation of the StartDate in format suitable for storage logging
     * @return String
     */
    public String toLogFormat() {
        if (this.isEmpty()) {
            return null;
        }
        return this.date.format(logFormatter);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof StartDate)) {
            throw new IllegalArgumentException(MESSAGE_ARGUMENT_CONSTRAINTS);
        }
        if (this.isEmpty() & ((StartDate) other).isEmpty()) {
            return true;
        }
        return this.date.equals(((StartDate) other).date);
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "";
        }
        return this.date.format(outputFormatter);
    }

    @Override
    public int hashCode() {
        return this.date.hashCode();
    }
}
