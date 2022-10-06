package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateOfBirth {

    public static final String MESSAGE_CONSTRAINTS = "Date of birth must be in format: ";

    public static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    public static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy");

    public final LocalDate date;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public DateOfBirth(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date, inputFormatter);
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidDate(String test) { //found from https://mkyong.com/java/how-to-check-if-date-is-valid-in-java/
        try {
            inputFormatter.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
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
