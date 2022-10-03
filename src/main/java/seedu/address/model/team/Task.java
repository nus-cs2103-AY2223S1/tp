package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Tasks are used to track the progress of a team.
 */
public class Task {

    public static final String MESSAGE_CONSTRAINTS =
        "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the task name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    /**
     * Name of the task.
     */
    private String name;

    /**
     * Constructs a {@code Task}.
     *
     * @param name A valid task name.
     */
    public Task(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.name = name;
    }

    /**
     * Returns true if a given string is a valid name for a task.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return name;
    }

    /**
     * Returns true if two tasks have the same name.
     *
     * @param other the other task to be compared with.
     * @return true if the tasks are considered equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Task // instanceof handles nulls
            && name.equals(((Task) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
