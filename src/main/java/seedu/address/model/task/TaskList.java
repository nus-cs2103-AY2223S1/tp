package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

/**
 * Represents a list of tasks for a particular person.
 *
 * Supports a minimal set of list operations.
 */
public class TaskList {
    private ArrayList<Task> internalTaskList;

    /**
     * Constructs a {@code TaskList}.
     */
    public TaskList(ArrayList<Task> tasks) {
        requireNonNull(tasks);
        internalTaskList = tasks;
    }

    /**
     * Adds a task to the list.
     */
    public void add(Task task) {
        requireNonNull(task);
        internalTaskList.add(task);
    }

    // TODO: add edit method

    /**
     * Removes the task from the list.
     */
    public Task delete(int index) {
        return internalTaskList.remove(index);
    }

    /**
     * Returns the size of the list.
     */
    public int size() {
        return internalTaskList.size();
    }

    /**
     * Returns true if the task list is empty/
     */
    public boolean isEmpty() {
        return this.internalTaskList.isEmpty();
    }

    /**
     * Returns the internal task list.
     */
    public ArrayList<Task> getTasks() {
        return internalTaskList;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        internalTaskList.forEach(t -> {
            int index = internalTaskList.indexOf(t);
                if (index == 0) {
                    builder.append(index + 1)
                            .append(". ")
                            .append(t);
                } else {
                    builder.append("\n")
                            .append(index + 1)
                            .append(". ")
                            .append(t);
                }
        });
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskList // instanceof handles nulls
                && internalTaskList.equals(((TaskList) other).internalTaskList));
    }

    @Override
    public int hashCode() {
        return internalTaskList.hashCode();
    }
}
