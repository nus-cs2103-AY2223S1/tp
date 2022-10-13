package jarvis.model;

import static jarvis.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Task description in JARVIS.
 */
public class TaskDesc {

    public static final String MESSAGE_CONSTRAINTS = "Task descriptions should not be blank";

    public final String taskDesc;

    /**
     * Constructs a {@code TaskDesc}.
     *
     * @param desc A valid description.
     */
    public TaskDesc(String desc) {
        requireNonNull(desc);
        checkArgument(isValidTaskDesc(desc), MESSAGE_CONSTRAINTS);
        taskDesc = desc;
    }

    /**
     * Returns true if a given string is a valid task description.
     */
    public static boolean isValidTaskDesc(String taskDesc) {
        return !taskDesc.isEmpty();
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
