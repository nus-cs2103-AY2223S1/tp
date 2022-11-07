package seedu.hrpro.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.hrpro.commons.util.AppUtil.checkArgument;

/**
 * Represent a task description in HR PRO Max++.
 */
public class TaskDescription {
    public static final String MESSAGE_CONSTRAINTS =
            "Task description should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String taskDescription;

    /**
     * Constructs a {@code TaskDescription}.
     *
     * @param description A valid description.
     */
    public TaskDescription(String description) {
        requireNonNull(description);
        checkArgument(isValidTaskDescription(description), MESSAGE_CONSTRAINTS);
        taskDescription = description;
    }

    /**
     * Returns true if a given string is a valid description.
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
                && taskDescription.equalsIgnoreCase(((TaskDescription) other).taskDescription)); // state check

    }

    @Override
    public int hashCode() {
        return taskDescription.hashCode();
    }
}
