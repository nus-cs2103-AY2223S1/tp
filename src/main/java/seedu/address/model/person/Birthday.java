package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ParserUtil.DATE_FORMAT_PATTERN;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Person's birthday in the address book.
 * Guarantees: immutable; is always valid
 */
public class Birthday {
    public static final String MESSAGE_CONSTRAINTS = "Birthdays should be in the format 'DD-MM-YYYY'";
    public static final String MESSAGE_INVALID_DATE = "The birthday provided is invalid!";
    public static final String MESSAGE_INVALID_BIRTHDAY = "The birthday provided is in the future!";

    public final LocalDate value;

    /**
     * Constructor for Birthday object.
     *
     * @param birthday
     */
    public Birthday(String birthday) {
        requireNonNull(birthday);
        value = LocalDate.parse(birthday, DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN)
                .withResolverStyle(ResolverStyle.STRICT));
    }

    @Override
    public String toString() {
        return value.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthday // instanceof handles nulls
                && value.equals(((Birthday) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Returns if a given string is a valid Date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN)
                    .withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException e) {
            System.out.println(e.getParsedString());
            System.out.println(e.getMessage());
            System.out.println(e);
            return false;
        }
        return true;
    }
}
