package seedu.address.model.task;

/**
 * Represents a Task in the TaskList.
 */
public class Task {
    private Description description;
    private boolean isDone;

    /**
     * A constructor that creates an instance of Task.
     * @param description The description of the task.
     */
    public Task(Description description) {
        this.isDone = false;
        this.description = description;
    }

    /**
     * Returns true if task is done, false if task is not done.
     * @return boolean indicating task completion status.
     */
    public boolean getTaskStatus() {
        return isDone;
    }

    /**
     * Marks task as done.
     */
    public void markTask() {
        isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void unmarkTask() {
        isDone = false;
    }
}
