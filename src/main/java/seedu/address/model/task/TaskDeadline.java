package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import seedu.address.model.task.exceptions.DatePastException;

/**
 * Represents the deadline of a task
 */
public class TaskDeadline {
    public static final String MESSAGE_CONSTRAINTS =
            "Deadline should be in YYYY-MM-DD format";
    private final LocalDate deadline;

    /**
     * Constructor for deadline.
     */
    public TaskDeadline(LocalDate date) {
        requireNonNull(date);
        if (LocalDate.now().isAfter(date)) {
            throw new DatePastException();
        }
        this.deadline = date;
    }

    /**
     * Returns the deadline of a task.
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    /**
     * Returns hashcode of the current object.
     */
    @Override
    public int hashCode() {
        return this.deadline.hashCode();
    }

    /**
     * Returns the string representation of the task deadline.
     */
    @Override
    public String toString() {
        return this.deadline.toString();
    }

    /**
     * Compares another object with the Deadline object.
     *
     * @param other the other object to be compared to
     * @return true if the two objects are equal
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDeadline // instanceof handles nulls
                && ((TaskDeadline) other).deadline.equals(this.deadline));
    }

    /**
     * Checks if this {@code deadline} is before another date.
     *
     * @param other the other date to compare {@code deadline} to
     * @return true if this {@code deadline} is before the other date
     */
    public boolean isBefore(LocalDate other) {
        return deadline.isBefore(other);
    }

    /**
     * Checks if this {@code deadline} is after another date.
     *
     * @param other the other date to compare {@code deadline} to
     * @return true if this {@code deadline} is after the other date
     */
    public boolean isAfter(LocalDate other) {
        return deadline.isAfter(other);
    }

    /**
     * Returns true if deadline is valid.
     *
     * @param test String to test
     * @return true if the String is a valid deadline
     */
    public static boolean isValidTaskDeadline(String test) {
        Boolean errorHappened = false;
        try {
            LocalDate value = LocalDate.parse(test);
        } catch (DateTimeParseException e) {
            errorHappened = true;
        }
        return !errorHappened;
    }
}
