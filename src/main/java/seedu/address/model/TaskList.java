package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.task.Task;

/**
 * Wraps all data at the Task List level
 */
public class TaskList implements ReadOnlyTaskList {

    private final ObservableList<Task> tasks;

    {
        tasks = FXCollections.observableArrayList();
    }

    public TaskList() {}

    /**
     * Creates the TaskList using Tasks in the {@code toBeCopied}
     */
    public TaskList(ReadOnlyTaskList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return FXCollections.unmodifiableObservableList(tasks);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks);
    }
    /**
     * Resets the existing data of this {@code TaskList} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskList newData) {
        requireNonNull(newData);
        setTasks(newData.getTaskList());
    }

    /**
     * Adds a task to the task list.
     */
    public void addTask(Task t) {
        requireNonNull(t);
        tasks.add(t);
    }

    /**
     * Check a task for duplicates
     */
    public boolean hasTask(Task t) {
        requireNonNull(t);
        return tasks.contains(t);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the task list.
     */
    public void setTask(Task target, Index targetIndex) {
        requireNonNull(target);
        requireNonNull(targetIndex);
        tasks.set(targetIndex.getZeroBased(), target);
    }

    /**
     * Deletes the task at the specified {@code Index}.
     */
    public void deleteTask(Index index) {
        requireNonNull(index);
        tasks.remove(index.getZeroBased());
    }

    @Override
    public int hashCode() {
        return this.tasks.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if they are the same object.
            || (other instanceof TaskList
            && tasks.hashCode() == (((TaskList) other).tasks).hashCode()); // temp only TODO: Implement UniqueTaskList
    }
}
