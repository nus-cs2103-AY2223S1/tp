package seedu.address.model.date;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a date in the application.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date implements Comparable<Date> {

    public static final String MESSAGE_CONSTRAINTS = "Dates must be in format: dd/MM/yyyy";

    //for checking if valid input date format
    private static final DateTimeFormatter checkFormatter = DateTimeFormatter
            .ofPattern("[d/M/yyyy][dd/M/yyyy][d/MM/yyyy][dd/MM/yyyy]");

    //for changing to storage friendly format
    private static final DateTimeFormatter logFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    //for changing to user-readable format
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy");

    public final LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        assert !date.isBlank();
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date, logFormatter);
    }

    /**
     * Returns true if a given string is a valid Date input.
     * @return boolean
     */
    //found from https://mkyong.com/java/how-to-check-if-date-is-valid-in-java/
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, checkFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Date d) {
        return this.date.compareTo(d.date);
    }

    /**
     * Returns the String representation of the Date in format suitable for storage logging
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

        if (!(other instanceof Date)) {
            return false;
        }

        Date sd = (Date) other;
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
