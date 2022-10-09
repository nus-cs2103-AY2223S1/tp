package seedu.address.model.task;

import java.util.Objects;

import seedu.address.model.module.Module;

/**
 * Task class represents a task which stores the module code and the
 * description of the task.
 */
public class Task {
    private final Module module;
    private final TaskDescription description;
    private boolean isComplete;

    /**
     * The constructor of the Task class. Sets the module and
     * description of the task.
     *
     * @param module The module being added.
     * @param description The description of the task.
     */
    public Task(Module module, TaskDescription description) {
        this.module = module;
        this.description = description;
        this.isComplete = false;
    }

    public TaskDescription getDescription() {
        return description;
    }


    public Module getModule() {
        return module;
    }

    /**
     * Returns true if both tasks have the same data fields.
     */
    public boolean isSameTask(Task otherTask) {
        return this.equals(otherTask);
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    /**
     * Marks the task as completed.
     */
    public void setComplete() {
        this.isComplete = true;
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
                && otherTask.getModule().equals(getModule())
                && otherTask.isComplete == isComplete;
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
