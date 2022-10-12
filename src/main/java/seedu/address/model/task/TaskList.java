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
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        internalTaskList = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with a given list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        requireNonNull(tasks);
        internalTaskList = tasks;
    }

    /**
     * Adds a task to the {@code TaskList},
     * @param task The task to be added.
     * @return The updated {@code TaskList} containing the added task.
     */
    public TaskList add(Task task) {
        ArrayList<Task> updatedTasks = new ArrayList<>(internalTaskList);
        updatedTasks.add(task);
        return new TaskList(updatedTasks);
    }

    /**
     * Edits a task in the {@code TaskList}.
     *
     * @param index of the task to be edited.
     * @param task that is updated.
     * @return The updated {@code TaskList}.
     */
    public TaskList edit(int index, Task task) {
        ArrayList<Task> updatedTasks = new ArrayList<>(internalTaskList);
        updatedTasks.set(index, task);
        return new TaskList(updatedTasks);
    }

    /**
     * Removes a task from the {@code TaskList}.
     *
     * @param index The index of the task to be deleted.
     * @return The updated {@code TaskList}.
     */
    public TaskList delete(int index) {
        ArrayList<Task> updatedTasks = new ArrayList<>(internalTaskList);
        updatedTasks.remove(index);
        return new TaskList(updatedTasks);
    }

    /**
     * Returns the task at the specified index.
     * @return
     */
    public Task get(int index) {
        return internalTaskList.get(index);
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
