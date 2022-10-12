package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Task in the address book.
 */
public class Task {

    private final Name name;
    private boolean isDone;

    /**
     * Constructs an unfinished {@code Task}.
     *
     * @param name A valid name.
     */
    public Task(Name name) {
        requireAllNonNull(name);
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructs a {@code Task} with specified state.
     *
     * @param name A valid name.
     * @param isDone The state.
     */
    public Task(Name name, boolean isDone) {
        requireAllNonNull(name);
        this.name = name;
        this.isDone = isDone;
    }


    public Name getName() {
        return this.name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        System.out.println("marked");
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
     * Returns true if both tasks have the same name.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName());
    }

    /**
     * Returns true if both task have the same description and both are done or both are not done.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Task // instanceof handles nulls
                && name.equals(((Task) other).getName())); // state check
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("[" + getStatusIcon() + "]" + getName());
        return builder.toString();
    }

}
