package jarvis.model;

import static java.util.Objects.requireNonNull;

/**
 * Represents a deadline field for a task in JARVIS.
 */
public class TaskDeadline {

    public final String deadline;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid description.
     */
    public TaskDeadline(String deadline) {
        requireNonNull(deadline);
        this.deadline = deadline;
    }


    @Override
    public String toString() {
        return deadline;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDeadline // instanceof handles nulls
                && deadline.equals(((TaskDeadline) other).deadline)); // state check
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }

}
