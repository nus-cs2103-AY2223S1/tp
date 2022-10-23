package seedu.uninurse.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task for a Patient.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskDescription(String)}
 */
public class Task {
    // consideration for v1.3: display status of task, which is to be
    // set in constructor since task is immutable

    public static final String MESSAGE_CONSTRAINTS = "Tasks can take any values, and it should not be blank";

    /*
     * The first character of the task must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String taskDescription;

    public final DateTime dateTime;

    /**
     * Constructs a {@code Task} with the given {@code description}.
     */
    public Task(String description) {
        requireNonNull(description);
        checkArgument(isValidTaskDescription(description), MESSAGE_CONSTRAINTS);
        taskDescription = description;
        dateTime = new DateTime();
    }

    /**
     * Constructs a {@code Task} with the given {@code description} and {@code dateAndTime}.
     */
    public Task(String description, DateTime dateAndTime) {
        requireNonNull(description);
        requireNonNull(description);
        checkArgument(isValidTaskDescription(description), MESSAGE_CONSTRAINTS);
        taskDescription = description;
        dateTime = dateAndTime;
    }

    /**
     * Returns true if a given string is a valid task description.
     */
    public static boolean isValidTaskDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean isTaskToday() {
        return dateTime.isToday();
    }

    @Override
    public String toString() {
        return taskDescription + " " + dateTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Task // instanceof handles nulls
                && taskDescription.equals(((Task) other).taskDescription)); // state check
    }

    @Override
    public int hashCode() {
        return taskDescription.hashCode();
    }
}
