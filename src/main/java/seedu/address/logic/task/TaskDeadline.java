package seedu.address.logic.task;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.task.exceptions.DatePastException;
import java.time.LocalDate;



/**
 * Represents the deadline of a task
 */
public class TaskDeadline {
    private LocalDate deadline;

    /**
     * constructor for deadline
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

}
