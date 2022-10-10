package modtrekt.model.task;

import modtrekt.model.module.ModCode;

/**
 * Represents a basic task in the task list.
 * Ensures that necessary details are valid, present and non-null.
 */
public class Task {

    /** String representing description of task */
    public final Description description;

    public final ModCode module;

    /**
     * Constructor for an instance of Task.
     *
     * @param description description of task
     */
    public Task(Description description, ModCode module) {
        this.description = description;
        this.module = module;
    }

    public Description getDescription() {
        return this.description;
    }

    public ModCode getModule() {
        return this.module;
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
                && o.getModule().equals(this.getModule());

    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        return other != null && (other instanceof Task)
                && ((Task) other).getDescription().equals(this.getDescription())
                && ((Task) other).getModule().equals(this.getModule());
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        result.append(getDescription())
                .append("; Module: ")
                .append(this.module.toString());

        return result.toString();
    }
}
