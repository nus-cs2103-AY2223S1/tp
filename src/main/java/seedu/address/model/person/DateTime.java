package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Patient's home-visit's date and time.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DateTime {

    public static final String MESSAGE_CONSTRAINTS = "Date and time should be in yyyy-MM-ddTHH:mm.\n"
            + "For example, 2022-11-11T17:35";

    /**
     * The Date can only be in yyyy-mm-ddTHH:mm format without any space.
     */

    // @@author xhphoong-reused
    // Reused from https://mkyong.com/regular-expressions/how-to-validate-date-with-regular-expression/ and
    // Reused from https://www.geeksforgeeks.org/how-to-validate-time-in-24-hour-format-using-regular-expression/
    public static final String VALIDATION_REGEX = "((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])"
            + "T" + "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    // @@author

    public final LocalDateTime dateTime;
    private final String dateTimeInString;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid datetime.
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        this.dateTime = LocalDateTime.parse(dateTime + ":00");
        this.dateTimeInString = dateTime;
    }

    public String getString() {
        return this.dateTimeInString;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDateTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime // instanceof handles nulls
                && dateTime.equals(((DateTime) other).dateTime)); // state check
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }

}
