package seedu.address.model.task;

import java.util.ArrayList;

/**
 * Represents a list that stores {@code Task}s.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a task into this {@code TaskList}.
     *
     * @param task The {@code Task} object to be added into this {@code TaskList}.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Returns the task in the specified index.
     *
     * @param index Index of the task.
     * @return The {@code Task} in the specified index.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns the number of {@code Task} in this {@code TaskList}.
     *
     * @return The number of {@code Task} in this <{@code TaskList}.
     */
    public int getSize() {
        return this.taskList.size();
    }
}
