package jarvis.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline field for a task in JARVIS.
 */
public class TaskDeadline {

    public static final String MESSAGE_CONSTRAINTS = "Deadlines should be in yyyy-MM-dd format";

    public final LocalDate deadline;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A LocalDate representation of the deadline.
     */
    public TaskDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean deadlineExists() {
        return deadline != null;
    }

    @Override
    public String toString() {
        return deadlineExists() ? deadline.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")) : "";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDeadline // instanceof handles nulls
                && deadline != null && ((TaskDeadline) other).deadline != null
                && deadline.equals(((TaskDeadline) other).deadline)); // state check
    }

    @Override
    public int hashCode() {
        return deadlineExists() ? deadline.hashCode() : 0;
    }
}
