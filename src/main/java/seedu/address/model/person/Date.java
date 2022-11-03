package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a date (can be nurse's unavailable date or fully-scheduled date).
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS = "Date should be in YYYY-MM-DD."
            + " For example, 2022-11-11";

    /*
     * https://mkyong.com/regular-expressions/how-to-validate-date-with-regular-
     * expression/
     * The Date can only be in YYYY-MM-DD format without any space.
     */
    public static final String VALIDATION_REGEX = "((?:20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";

    public final LocalDate date;
    private final String dateInString;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date);
        this.dateInString = date;
    }

    /**
     * Constructs a {@code Date}.
     */
    public Date(LocalDate date) {
        requireNonNull(date);
        this.date = date;
        this.dateInString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getString() {
        return this.dateInString;
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                        && date.equals(((Date) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    public LocalDate getDate() {
        return this.date;
    }

}
