package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateOfBirth {

    public static final String MESSAGE_CONSTRAINTS = "Date of birth must be in format: ";
    private static final String MESSAGE_ARGUMENT_CONSTRAINTS = "compareTo() of DateOfBirth must take in argument of type LocalDate";

    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy");

    public final LocalDate date;

    private boolean isEmpty;

    public DateOfBirth() {
        this.date = null;
        this.isEmpty = true;
    }

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public DateOfBirth(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date, inputFormatter);
        this.isEmpty = false;
    }

    public static DateOfBirth empty() {
        return new DateOfBirth();
    }

    /**
     * Returns true if a given string is a valid DOB to be logged.
     */
    public static boolean isValidLogDate(String test) { //found from https://mkyong.com/java/how-to-check-if-date-is-valid-in-java/
        try {
            outputFormatter.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

     /**
     * Returns true if a given string is a valid DOB input.
     */
    public static boolean isValidDate(String test) { //found from https://mkyong.com/java/how-to-check-if-date-is-valid-in-java/
        try {
            inputFormatter.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public int compareTo(Object other) {
        if (!(other instanceof LocalDate)) {
            throw new IllegalArgumentException(MESSAGE_ARGUMENT_CONSTRAINTS);
        }
        return this.date.compareTo((LocalDate) other);
    }

    public boolean isEmpty() {
        return this.isEmpty;
    }

    public String toLogFormat() {
        return this.date.format(inputFormatter);
    }

    @Override
    public String toString() {
        return this.date.format(outputFormatter);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LocalDate // instanceof handles nulls
                && date.equals((LocalDate) other)); // state check
    }

    @Override
    public int hashCode() {
        return this.date.hashCode();
    }
}
