package modtrekt.model.task;

/**
 * Represents a basic immutable task in the task list.
 * Ensures that necessary details are valid, present and non-null.
 */
public class Task {
    /**
     * String representing description of task
     */
    private final Description description;
    private boolean isArchived;

    /**
     * Constructor for an instance of Task.
     *
     * @param description description of task
     */
    public Task(Description description, boolean isArchived) {
        this.description = description;
        this.isArchived = isArchived;
    }

    public Task(Description description) {
        this(description, false);
    }

    public Description getDescription() {
        return this.description;
    }

    public boolean isArchived() {
        return this.isArchived;
    }

    public void archive() {
        this.isArchived = true;
    }

    public void unarchive() {
        this.isArchived = false;
    }

    /**
     * Compares task object with another task object and checks if they are the same.
     */
    public boolean isSameTask(Task o) {
        if (this == o) {
            return true;
        }
        return o != null && o.getDescription().equals(this.getDescription()) && o.isArchived() == this.isArchived();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof Task) && this.isSameTask((Task) other);
    }

    @Override
    public String toString() {
        return this.description.toString();
    }
}
