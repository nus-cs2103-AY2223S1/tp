package paymelah.model.debt;

import static java.util.Objects.requireNonNull;
import static paymelah.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Debt's date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class DebtDate implements Comparable<DebtDate> {

    public static final String MESSAGE_CONSTRAINTS =
            "Date should be a valid date in yyyy-mm-dd format; where y is year, m is month and d is day.";
    public static final DateTimeFormatter DATE_INPUT_FORMAT = DateTimeFormatter.ofPattern("uuuu-M-d")
            .withResolverStyle(ResolverStyle.STRICT);
    public static final DateTimeFormatter DATE_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("uuuu-MM-dd");

    private final LocalDate date;

    /**
     * Constructs a {@code DebtDate}.
     *
     * @param date A valid date.
     */
    public DebtDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date, DATE_INPUT_FORMAT);
    }

    /**
     * Constructs a {@code DebtDate} with the current date.
     */
    public DebtDate() {
        this.date = LocalDate.now();
    }

    /**
     * Returns true if a given string is a valid date in the correct format.
     *
     * @param test The string to test for validity.
     * @return true if the given string is a valid date in the correct format.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, DATE_INPUT_FORMAT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return date.format(DATE_OUTPUT_FORMAT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DebtDate // instanceof handles nulls
                && date.equals(((DebtDate) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    @Override
    public int compareTo(DebtDate o) {
        return this.date.compareTo(o.date);
    }
}
