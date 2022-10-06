package seedu.address.model.task;

/**
 * Represents a task in the tasklist.
 */
public class Task {
    private Description description;
    private boolean isDone;

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
