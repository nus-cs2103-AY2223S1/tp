package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Task in the task list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    private final Description description;
    private final Module module;

    /**
     * Every field must be present and not null.
     */
    public Task(Module module, Description description) {
        requireAllNonNull(module, description);
        this.module = module;
        this.description = description;
    }

    public Module getModule() {
        return module;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns true if both tasks have the same data fields.
     */
    public boolean isSameTask(Task otherTask) {
        return this.equals(otherTask);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getDescription().equals(getDescription())
                && otherTask.getModule().equals(getModule());
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, module);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getModule())
                .append("; Description: ")
                .append(getDescription());
        return builder.toString();
    }
}
