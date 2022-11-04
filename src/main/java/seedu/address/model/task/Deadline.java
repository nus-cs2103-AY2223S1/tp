package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Represents a Task's deadline in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline implements Comparable<Deadline> {

    public static final String MESSAGE_CONSTRAINTS =
            "The date provided should be a valid date of the form YYYY-MM-DD, eg 2022-10-07";

    // Original value passed into constructor - used for storage in JSON
    public final String value;
    private final Optional<LocalDate> date;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param date A valid deadline.
     */
    public Deadline(String date) {
        requireNonNull(date);
        checkArgument(isValidDeadline(date), MESSAGE_CONSTRAINTS);
        this.value = date;
        this.date = parse(date);
    }

    /**
     * Returns an empty <code>Deadline</code>, which represents a lack of a deadline.
     */
    public static Deadline empty() {
        return new Deadline("");
    }

    private static Optional<LocalDate> parse(String text) {
        if (text.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(LocalDate.parse(text));
    }

    /**
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String text) {
        if (text.isEmpty()) {
            return true;
        }

        try {
            Deadline.parse(text);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns the formatted date to be displayed in the UI.
     * @return A formatted string representing the date
     */
    public String toDisplayString() {
        return this.date.map(LocalDate::toString).orElseGet(String::new);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && date.equals(((Deadline) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    /**
     * An internal helper function used to provide an "effective" date for
     * the `compareTo` method. In this case, all `Deadline`s with no dates
     * are treated to be due "far" in the future.
     */
    private LocalDate effectiveDate() {
        assert this.date != null : "Deadline class should not have a null date";
        return this.date.orElse(LocalDate.MAX);
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this date is before,
     * equal to, or after the compared deadline.
     *
     * An empty deadline is considered to be "far in the future", meaning that
     * if compared to any non-empty deadline, a positive integer will be returned.
     */
    @Override
    public int compareTo(Deadline o) {
        return this.effectiveDate().compareTo(o.effectiveDate());
    }
}
