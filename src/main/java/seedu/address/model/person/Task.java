package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's tasks in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTask(String)}
 */
public class Task {

    public static final String MESSAGE_CONSTRAINTS = "Tasks can take any values, and it should not be blank";

    /*
     * The first character of a task must not be a whitespace
     * other " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\S].*";

    public final String value;

    /**
     * Constructs a {@code Task}
     */
    public Task(String taskDescription) {
        requireNonNull(taskDescription);
        checkArgument(isValidTask(taskDescription), MESSAGE_CONSTRAINTS);
        value = taskDescription;
    }

    /**
     * Returns true if a given string is a valid task.
     */
    public static boolean isValidTask(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Task //instanceof handles nulls
            && value.equals(((Task) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
