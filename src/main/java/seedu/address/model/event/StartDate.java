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

    //for checking if valid input date format
    private static final DateTimeFormatter checkFormatter = DateTimeFormatter
            .ofPattern("[d/M/yyyy][dd/M/yyyy][d/MM/yyyy][dd/MM/yyyy]");

    //for changing to storage friendly format
    private static final DateTimeFormatter logFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    //for changing to user-readable format
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy");

    public final LocalDate date;

    /**
     * Constructs a {@code StartDate}.
     *
     * @param date A valid date.
     */
    public StartDate(String date) {
        requireNonNull(date);
        assert !date.isBlank();
        checkArgument(isValidStartDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date, logFormatter);
    }

    /**
     * Returns true if a given string is a valid StartDate input.
     * @return boolean
     */
    //found from https://mkyong.com/java/how-to-check-if-date-is-valid-in-java/
    public static boolean isValidStartDate(String test) {
        try {
            LocalDate.parse(test, checkFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    //TODO: To be re-implemented by Benjamin for Sort By Date
    ///**
    // * Returns 1 if the other object is a StartDate that is later,
    // * -1 if the other object is a StartDate that is earlier,
    // * and 0 if the other object is a StartDate that is of the same date.
    // * @param other The object to compare with
    // * @return int
    // */
    //public int compareTo(Object other) {
    //    if (other == null) {
    //        return -1;
    //    }
    //    if (!(other instanceof StartDate)) {
    //        throw new IllegalArgumentException(MESSAGE_ARGUMENT_CONSTRAINTS);
    //    }
    //    if (this.isEmpty() & ((StartDate) other).isEmpty()) {
    //        return 0;
    //    }
    //    return this.date.compareTo(((StartDate) other).date);
    //}

    /**
     * Returns the String representation of the StartDate in format suitable for storage logging
     * @return String
     */
    public String toLogFormat() {
        return this.date.format(logFormatter);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof StartDate)) {
            return false;
        }

        StartDate sd = (StartDate) other;
        return this.date.equals(sd.date);
    }

    @Override
    public String toString() {
        return this.date.format(outputFormatter);
    }

    @Override
    public int hashCode() {
        return this.date.hashCode();
    }
}
