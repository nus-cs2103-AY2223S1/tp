package seedu.address.model.date;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * Represents a date in the application.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateFormat(String)}
 * and {@link #isValidDateValue(String)}.
 */
public class Date implements Comparable<Date> {
    public static final String MESSAGE_CONSTRAINTS = "Dates must be in format: DD/MM/YYYY";

    public static final String MESSAGE_CONSTRAINTS_DOB = "Date of birth cannot be set to after the current date.";

    public static final String MESSAGE_VALUE_CONSTRAINTS = "%s exceeds the range of valid date values.";
    private static final String DATE_DELIMITER = "/";
    private static final String VALIDATION_REGEX = "\\d{2}/\\d{2}/\\d{4}";

    //for changing to storage friendly format
    private static final DateTimeFormatter logFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
        checkArgument(isValidDateFormat(date), MESSAGE_CONSTRAINTS);
        checkArgument(isValidDateValue(date), String.format(MESSAGE_VALUE_CONSTRAINTS, date));
        this.date = LocalDate.parse(date, logFormatter);
    }

    /**
     * Checks if a given string is a valid Date input.
     */
    public static boolean isValidDate(String test) {
        return isValidDateFormat(test) && isValidDateValue(test);
    }

    /**
     * Checks if a given string follows the valid Date input format.
     */
    public static boolean isValidDateFormat(String dateToTest) {
        requireNonNull(dateToTest);
        return dateToTest.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid Date input.
     */
    public static boolean isValidDateValue(String dateToTest) {
        requireNonNull(dateToTest);
        try {
            String[] parsedDate = dateToTest.split(DATE_DELIMITER, 3);
            //To catch corner case of Year 0 which should not exist but #of is unable to detect
            if (Integer.parseInt(parsedDate[2]) == 0) {
                return false;
            }
            LocalDate.of(Integer.parseInt(parsedDate[2]), Integer.parseInt(parsedDate[1]),
                    Integer.parseInt(parsedDate[0]));
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if a given string is after the current date.
     * @param dateToTest string input, the format check for test should be done before calling this function.
     */
    public static boolean isAfterCurrentDate(String dateToTest) {
        LocalDate dateToCheck = LocalDate.parse(dateToTest, logFormatter);
        return dateToCheck.isAfter(LocalDate.now());
    }

    /**
     * Returns the integer age of this Date object when it is used as the date of birth of a person.
     */
    public int toAge() {
        LocalDate currDate = LocalDate.now();
        return Period.between(this.date, currDate).getYears();
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
