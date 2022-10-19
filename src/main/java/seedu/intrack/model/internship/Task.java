package seedu.intrack.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Represents an Internship's application task.
 * Guarantees: details are present and not null
 */
public class Task {

    public static final String MESSAGE_CONSTRAINTS = "Task names can take any values, and it should not be blank";
    public static final String TIME_CONSTRAINTS = "Time must be in the format dd-MM-yyyy HH:mm";

    /*
     * The first character of the task must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public final String taskName;
    public final LocalDateTime taskTime;

    /**
     * Constructs an {@code Task}
     */
    public Task() {
        this.taskName = "Application submitted";
        this.taskTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    }

    /**
     * Constructs an {@code Task}.
     *
     * @param taskName A valid task name.
     * @param taskTime A valid task time.
     */
    public Task(String taskName, String taskTime) {
        requireNonNull(taskName);
        requireNonNull(taskTime);
        checkArgument(isValidTaskName(taskName), MESSAGE_CONSTRAINTS);
        checkArgument(isValidTaskTime(taskTime), TIME_CONSTRAINTS);
        this.taskName = taskName;
        this.taskTime = LocalDateTime.parse(taskTime, FORMATTER);
    }

    /**
     * Returns true if a given string is a valid task name.
     */
    public static boolean isValidTaskName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid task time.
     */
    public static boolean isValidTaskTime(String test) {
        try {
            FORMATTER.parse(test);
        } catch (DateTimeParseException dtpe) {
            return false;
        }
        return true;
    }

    public String getTaskName() {
        return taskName;
    }

    public LocalDateTime getTaskTime() {
        return taskTime;
    }

    @Override
    public String toString() {
        return taskName + " at " + taskTime.format(FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Task // instanceof handles nulls
                && taskName.equals(((Task) other).taskName))
                && taskTime.equals(((Task) other).taskTime); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, taskTime);
    }
}
