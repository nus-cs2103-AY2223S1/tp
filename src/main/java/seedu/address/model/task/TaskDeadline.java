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
            "Date should be in YYYY-MM-DD format";
    private final LocalDate deadline;

    /**
     * constructor for deadline
     *
     * @param date
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
     *
     * @return The task category.
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    /**
     * Returns hashcode of the current object
     *
     * @return Hashcode of the object.
     */
    @Override
    public int hashCode() {
        return this.deadline.hashCode();
    }

    /**
     * Returns the string representation of the task deadline.
     *
     * @return Task priority.
     */
    @Override
    public String toString() {
        return this.deadline.toString();
    }

    /**
     * Compares another object with the Deadline object.
     *
     * @param other The other object to be compared to.
     * @return If the two objects are equal.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDeadline // instanceof handles nulls
                && ((TaskDeadline) other).deadline.equals(this.deadline));
    }

    /**
     * Checks if this {@code deadline} is before the {@code deadline} of another TaskDeadline.
     * @param other the other TaskDeadline to compare {@code deadline} to
     * @return true if this {@code deadline} is before the {@code deadline} of the other TaskDeadline
     */
    public boolean isBefore(TaskDeadline other) {
        return deadline.isBefore(other.getDeadline());
    }

    /**
     * Checks if this {@code deadline} is after the {@code deadline} of another TaskDeadline.
     * @param other the other TaskDeadline to compare {@code deadline} to
     * @return true if this {@code deadline} is after the {@code deadline} of the other TaskDeadline
     */
    public boolean isAfter(TaskDeadline other) {
        return deadline.isAfter(other.getDeadline());
    }

    /**
     * Returns true if deadline is valid.
     *
     * @param test String to test.
     * @return Whether the String is a valid deadline.
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
