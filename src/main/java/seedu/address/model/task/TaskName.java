package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task Name in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class TaskName {

    public static final String MESSAGE_CONSTRAINTS =
            "Task names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String taskName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid task name.
     */
    public TaskName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        taskName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return taskName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskName // instanceof handles nulls
                && taskName.equals(((TaskName) other).taskName)); // state check
    }

    @Override
    public int hashCode() {
        return taskName.hashCode();
    }
}
