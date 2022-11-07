package seedu.address.model.task;

import static java.util.Objects.isNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a Task in the address book.
 */
public class Task {

    public static final String MESSAGE_INVALID_DATE_FORMAT = "Deadline must follow the dd-MM-yyyy format "
            + ", must be a valid date and in between year 1900 and 2100.";
    public static final String MESSAGE_INVALID_DATE_VALUE = "Deadline must be in between the year 1900 and 2100";

    private static final LocalDate EARLIEST_DATE = LocalDate.of(1899, 12, 31);
    private static final LocalDate LATEST_DATE = LocalDate.of(2101, 1, 1);

    private TaskName taskName;
    private Optional<LocalDate> deadline;
    private boolean isDone;

    /**
     * Constructs an unfinished {@code Task}.
     *
     * @param taskName A valid name.
     * @param deadline A valid date or null.
     */
    public Task(TaskName taskName, LocalDate deadline) {
        requireAllNonNull(taskName);
        this.taskName = taskName;
        this.deadline = Optional.ofNullable(deadline);
        this.isDone = false;
    }

    /**
     * Constructs a {@code Task} with specified state.
     *
     * @param taskName A valid name.
     * @param deadline A valid date or null.
     * @param isDone The state.
     */
    public Task(TaskName taskName, LocalDate deadline, boolean isDone) {
        requireAllNonNull(taskName);
        this.taskName = taskName;
        this.deadline = Optional.ofNullable(deadline);
        this.isDone = isDone;
    }

    /**
     * Gets the name of the task.
     *
     * @return TaskName for the given task.
     */
    public TaskName getName() {
        return this.taskName;
    }

    /**
     * Edits the taskname and deadline of the Task
     *
     * @param newTaskName The new task name.
     * @param newDeadline The new deadline provided.
     */
    public void editTaskDesc(TaskName newTaskName, LocalDate newDeadline) {
        if (!isNull(newTaskName)) {
            this.taskName = newTaskName;
        }
        if (!isNull(newDeadline)) {
            this.deadline = Optional.ofNullable(newDeadline);
        }
    }

    /**
     * Gets the deadline of the task.
     *
     * @return The deadline of the given task.
     */
    public Optional<LocalDate> getDeadline() {
        return this.deadline;
    }

    public static boolean isValidDeadline(LocalDate deadline) {
        return deadline.isAfter(EARLIEST_DATE) && deadline.isBefore(LATEST_DATE);
    }

    /**
     * Gets the deadline of the task.
     *
     * @return The deadline of the given task in String format.
     */
    public String getDeadlineString() {
        Optional<String> date = getDeadline().map(LocalDate::toString);
        return date.orElse("");
    }

    /**
     * Shows whether the task is done or not
     *
     * @return The boolean whether the task is done or not.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns 'X' if done or " " otherwise.
     * @return String "X" if the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns true if both tasks have the same name and deadline.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName())
                && otherTask.getDeadline().equals(getDeadline());
    }

    /**
     * Returns true if both task have the same description, same deadline and both are done or both are not done.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Task // instanceof handles nulls
                && taskName.equals(((Task) other).getName())
                && getDeadline().equals(((Task) other).getDeadline()))
                && getIsDone() == ((Task) other).getIsDone(); // state check
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(taskName);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("[" + getStatusIcon() + "] " + getName());

        if (getDeadline().isPresent()) {
            builder.append(" (by: " + getDeadline().get() + ")");
        }
        return builder.toString();
    }

}
