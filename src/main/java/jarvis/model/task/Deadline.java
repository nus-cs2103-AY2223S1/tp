package jarvis.model.task;

import static java.util.Objects.requireNonNull;

/**
 * Represents a deadline field for a task in JARVIS.
 */
public class Deadline {

    public final String deadline;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid description.
     */
    public Deadline(String deadline) {
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
                || (other instanceof Deadline // instanceof handles nulls
                && deadline.equals(((Deadline) other).deadline)); // state check
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }

}
