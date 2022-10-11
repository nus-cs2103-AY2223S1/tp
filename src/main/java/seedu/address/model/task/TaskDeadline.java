package seedu.address.model.task;
import java.time.LocalDate;

/**
 * Represents the deadline of a task
 */
public class TaskDeadline {
    private LocalDate deadline;

    public TaskDeadline(LocalDate level) {
        this.deadline = level;
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
