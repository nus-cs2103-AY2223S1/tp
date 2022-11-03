package seedu.address.model.date;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a date in the application.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date implements Comparable<Date> {
    public static final String MESSAGE_CONSTRAINTS = "Dates must be in format: DD/MM/YYYY";

    public static final String MESSAGE_CONSTRAINTS_DOB = "Date must be before current date for date of births";

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
     * @param date a valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date, logFormatter);
    }

    /**
     * Returns true if a given string is a valid Date input.
     * @return boolean
     */
    //Solution below adapted from https://mkyong.com/java/how-to-check-if-date-is-valid-in-java/
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, checkFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if the {@code LocalDate} specified is after the current date, false otherwise.
     */
    public static boolean isAfterCurrentDate(String date) {
        LocalDate dateToCheck = LocalDate.parse(date, logFormatter);
        return dateToCheck.isAfter(LocalDate.now());
    }

    @Override
    public int compareTo(Date d) {
        return this.date.compareTo(d.date);
    }

    /**
     * Returns the String representation of the Date in a format suitable for storage logging.
     * @return String the string that is in the correct format for logging.
     */
    public String toLogFormat() {
        return this.date.format(logFormatter);
    }

    /**
     * Returns the integer age of a person.
     */
    public int toAge() {
        LocalDate currDate = LocalDate.now();
        return Period.between(this.date, currDate).getYears();
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
