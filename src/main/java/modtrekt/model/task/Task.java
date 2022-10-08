package modtrekt.model.task;

/**
 * Represents a basic task in the task list.
 * Ensures that necessary details are valid, present and non-null.
 */
public class Task {

    /** String representing description of task */
    public final Description description;

    /**
     * Constructor for an instance of Task.
     *
     * @param description description of task
     */
    public Task(Description description) {
        this.description = description;
    }

    public Description getDescription() {
        return this.description;
    }

    /**
     * Compares task object with another task object and checks if they are the same.
     */
    public boolean isSameTask(Task o) {
        if (this == o) {
            return true;
        }

        return o != null
                && o.getDescription().equals(this.getDescription());

    }

    @Override
    public String toString() {
        return this.description.toString();
    }
}
