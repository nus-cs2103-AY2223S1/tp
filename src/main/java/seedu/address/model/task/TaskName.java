package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's name in the task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class TaskName {

    public static final String MESSAGE_CONSTRAINTS = "Names should not be blank";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\S][\\S ]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public TaskName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.task.TaskName // instanceof handles nulls
                && fullName.equals(((seedu.address.model.task.TaskName) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
