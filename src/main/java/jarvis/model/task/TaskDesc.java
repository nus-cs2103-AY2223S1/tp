package jarvis.model.task;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Task description in JARVIS.
 */
public class TaskDesc {

    public final String taskDesc;

    /**
     * Constructs a {@code TaskDesc}.
     *
     * @param desc A valid description.
     */
    public TaskDesc(String desc) {
        requireNonNull(desc);
        taskDesc = desc;
    }


    @Override
    public String toString() {
        return taskDesc;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDesc // instanceof handles nulls
                && taskDesc.equals(((TaskDesc) other).taskDesc)); // state check
    }

    @Override
    public int hashCode() {
        return taskDesc.hashCode();
    }

}
