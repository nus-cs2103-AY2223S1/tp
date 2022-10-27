package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents the dates that all TaskDeadline dates can fall under
 */
public class TaskDate {
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in YYYY-MM-DD format";
    private final LocalDate date;

    /**
     * Constructor method for {@code TaskDate} class.
     */
    public TaskDate(LocalDate date) {
        requireNonNull(date);
        this.date = date;
    }

    /**
     * Returns the date.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns hashcode of the current object.
     */
    @Override
    public int hashCode() {
        return this.date.hashCode();
    }

    /**
     * Returns the string representation of the date.
     */
    @Override
    public String toString() {
        return this.date.toString();
    }

    /**
     * Compares another object with this TaskDate object.
     * @param other The other object to be compared to
     * @return true if the two objects are equal
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDate // instanceof handles nulls
                && ((TaskDate) other).date.equals(this.date));
    }

    /**
     * Checks if this {@code date} is before another date.
     * @param other the other date to compare {@code date} to
     * @return true if this {@code date} is before the other date
     */
    public boolean isBefore(LocalDate other) {
        return date.isBefore(other);
    }

    /**
     * Checks if this {@code date} is after another date.
     * @param other the other date to compare {@code date} to
     * @return true if this {@code date} is after the other date
     */
    public boolean isAfter(LocalDate other) {
        return date.isAfter(other);
    }

    /**
     * Returns true if date is valid.
     * @param test String to test
     * @return true if the String is a valid date
     */
    public static boolean isValidTaskDate(String test) {
        Boolean errorHappened = false;
        try {
            LocalDate value = LocalDate.parse(test);
        } catch (DateTimeParseException e) {
            errorHappened = true;
        }
        return !errorHappened;
    }
}
