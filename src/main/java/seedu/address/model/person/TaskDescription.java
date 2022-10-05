package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's task to be done in the Patient list.
 */
public class TaskDescription {

    public static final String MESSAGE_CONSTRAINTS =
            "Task Description should only contain alphanumeric characters and spaces";

    public static final String VALIDATION_REGEX = "[\\p{Alnum} ]*";
    public final String taskDescription;

    /**
     * Constructs a {@code TaskDescription}.
     *
     * @param description A valid task description.
     */
    public TaskDescription(String description) {
        requireNonNull(description);
        checkArgument(isValidTaskDescription(description), MESSAGE_CONSTRAINTS);
        taskDescription = description;
    }

    /**
     * Checks the validity of the task description.
     *
     * @param test the string to be tested.
     * @return true if valid.
     */
    public static boolean isValidTaskDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return taskDescription;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDescription // instanceof handles nulls
                && taskDescription.equals(((TaskDescription) other).taskDescription)); // state check
    }

    @Override
    public int hashCode() {
        return taskDescription.hashCode();
    }
}
