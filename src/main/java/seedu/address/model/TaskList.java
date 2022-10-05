package seedu.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Wraps all data at the Task List level
 */
public class TaskList implements ReadOnlyTaskList {

    private final List<Task> tasks;

    {
        tasks = new ArrayList<>();
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
        return FXCollections.observableList(tasks);
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
        tasks.add(t);
    }

    @Override
    public int hashCode() {
        return this.tasks.hashCode();
    }
}
