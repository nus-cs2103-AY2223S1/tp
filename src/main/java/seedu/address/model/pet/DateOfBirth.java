package seedu.address.model.pet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ModelManager;

/**
 * Abstracts the date of birth of a pet.
 */
public class DateOfBirth {

    public static final String MESSAGE_USAGE = "The date of birth should be in this format preferably: "
            + ModelManager.PREFERRED_DATE_FORMAT;
    private static final String[] ACCEPTABLE_DATE_FORMATS = ModelManager.ACCEPTABLE_DATE_FORMATS;

    private static final String PREFERRED_DATE_FORMAT = ModelManager.PREFERRED_DATE_FORMAT;

    private static final DateTimeFormatter PREFERRED_FORMATTER = ModelManager.PREFERRED_FORMATTER;

    private final LocalDate date;

    public DateOfBirth(LocalDate date) {
        this.date = date;
    }

    /**
     * Parses the date from a string representation.
     *
     * @param toParse The string to be parsed.
     * @return The DateOfBirth object.
     * @throws IllegalValueException If the string cannot be parsed by any acceptable date format
     */
    public static DateOfBirth parseString(String toParse) throws IllegalValueException {
        LocalDate output;
        for (String format: ACCEPTABLE_DATE_FORMATS) {
            try {
                output = LocalDate.parse(toParse, DateTimeFormatter.ofPattern(format));
                return new DateOfBirth(output);
            } catch (DateTimeParseException exception) {
                //Do nothing because it will eventually throw an exception if no formats match
            }
        }
        throw new IllegalValueException(MESSAGE_USAGE);
    }

    @Override
    public String toString() {
        return date.format(PREFERRED_FORMATTER);
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
