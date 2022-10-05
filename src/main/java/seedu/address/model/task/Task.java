package seedu.address.model.task;

public class Task {
    protected Description description;
    protected boolean isDone;

    /**
     * A constructor that creates an instance of Task.
     *
     * @param description The description of the task.
     */
    public Task(Description description) {
        this.isDone = false;
        this.description = description;
    }

    /**
     * Returns a String that reflects whether this task is done or not done.
     *
     * @return Returns an "X" or " " depending on whether this task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "  "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon()  + "] " + description;
    }

    /**
     * Returns a String that is written to a file to be stored.
     *
     * @return A String formatted to be stored.
     */
    public String toStore() {
        if (isDone) {
            return " : 1 : " + description;
        } else {
            return " : 0 : " + description;
        }
    }
}
