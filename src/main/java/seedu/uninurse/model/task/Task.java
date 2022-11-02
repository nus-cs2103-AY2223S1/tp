package seedu.uninurse.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.util.AppUtil.checkArgument;
import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

/**
 * Represents a Task for a Patient.
 *  * Guarantees: immutable; is valid as declared in {@link #isValidTaskDescription(String)}
 */
public abstract class Task {

    public static final String MESSAGE_CONSTRAINTS = "Task description can take any values, and it should not be blank";

    /*
     * The first character of the task must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String taskDescription;

    private final DateTime dateTime;

    /**
     * Constructs a {@code Task} with the given {@code description}.
     */
    protected Task(String description) {
        requireNonNull(description);
        checkArgument(isValidTaskDescription(description), MESSAGE_CONSTRAINTS);
        taskDescription = description;
        dateTime = new DateTime();
    }

    /**
     * Constructs a {@code Task} with the given {@code description} and {@code dateAndTime}.
     */
    protected Task(String description, DateTime dateAndTime) {
        requireAllNonNull(description, dateAndTime);
        checkArgument(isValidTaskDescription(description), MESSAGE_CONSTRAINTS);
        taskDescription = description;
        dateTime = dateAndTime;
    }

    /**
     * Returns if the Task is today.
     */
    public boolean isTaskToday() {
        return dateTime.isToday();
    }

    /**
     * Returns if the Task is on the given {@code DateTime}.
     */
    public boolean isTaskOnDay(DateTime test) {
        return dateTime.isDate(test);
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns whether the task date is past the current date.
     */
    public boolean passedTaskDate() {
        return dateTime.isPastDate();
    }

    /**
     * TODO
     */
    public abstract List<Task> updateTask(List<Task> taskList);

    /**
     * Returns the String representation of the recurrence.
     */
    public abstract String getRecurrenceString();

    /**
     * Returns true if a given string is a valid task description.
     */
    public static boolean isValidTaskDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return taskDescription + " " + dateTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Task // instanceof handles nulls
                && taskDescription.equals(((Task) other).taskDescription)
                && dateTime.equals(((Task) other).dateTime)); // state check
    }

    @Override
    public int hashCode() {
        return taskDescription.hashCode();
    }
}
