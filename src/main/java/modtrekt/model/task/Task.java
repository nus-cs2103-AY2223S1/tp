package modtrekt.model.task;

import modtrekt.model.module.ModCode;

/**
 * Represents a basic immutable task in the task list.
 * Ensures that necessary details are valid, present and non-null.
 */
public class Task {
    /**
     * String representing description of task
     */
    public final ModCode module;
    private final Description description;
    private final boolean isArchived;

    /**
     * Constructor for an instance of Task.
     *
     * @param description description of task
     */
    public Task(Description description, ModCode module, boolean isArchived) {
        this.description = description;
        this.module = module;
        this.isArchived = isArchived;
    }

    /**
     * Constructor for an instance of Task, with a default unarchived state.
     *
     * @param description description of task
     * @param module module code of task
     */
    public Task(Description description, ModCode module) {
        this.description = description;
        this.module = module;
        this.isArchived = false;
    }

    public Description getDescription() {
        return this.description;
    }

    public ModCode getModule() {
        return this.module;
    }

    public boolean isArchived() {
        return this.isArchived;
    }

    public Task archive() {
        return new Task(this.description, this.module, true);
    }

    public Task unarchive() {
        return new Task(this.description, this.module, false);
    }

    /**
     * Compares task object with another task object and checks if they are the same.
     */
    public boolean isSameTask(Task o) {
        if (this == o) {
            return true;
        }

        return o != null
                && o.getDescription().equals(this.getDescription())
                && o.getModule().equals(this.getModule())
                && o.isArchived() == this.isArchived();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof Task) && isSameTask((Task) other);
    }

    @Override
    public String toString() {
        return String.format("%s %s%s",
                description,
                module,
                isArchived ? "(ARCHIVED)" : ""
        );
    }
}
