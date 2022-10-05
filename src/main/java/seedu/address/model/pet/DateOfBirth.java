package seedu.address.model.pet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.commons.exceptions.IllegalValueException;

public class DateOfBirth {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    private final LocalDate date;

    DateOfBirth(LocalDate date) {
        this.date = date;
    }

    public static DateOfBirth parseString(String toParse) throws IllegalValueException {
        LocalDate output;
        try {
            output = LocalDate.parse(toParse, FORMATTER);
        } catch (DateTimeParseException exception) {
            throw new IllegalValueException("The date of birth should be in format: " + DATE_TIME_FORMAT);
        }
        return new DateOfBirth(output);
    }

    @Override
    public String toString() {
        return date.format(FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateOfBirth // instanceof handles nulls
                && date.equals(((DateOfBirth) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    public LocalDate getDate() {
        return date;
    }
}
