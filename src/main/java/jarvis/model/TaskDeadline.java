package jarvis.model;

import static java.util.Objects.requireNonNull;

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
        requireNonNull(deadline);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }
        if (!(other instanceof TaskDeadline)) { // instanceof handles nulls
            return false;
        }
        TaskDeadline otherTaskDeadline = (TaskDeadline) other;
        return deadline.equals(otherTaskDeadline.deadline);
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }
}
