package seedu.codeconnect.model.task;

import static seedu.codeconnect.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.codeconnect.model.module.Module;


/**
 * Represents a Task in the task list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    private final TaskName name;
    private final Module module;
    private final Deadline deadline;
    private final Status status;

    /**
     * Constructs a new Task. Every field must be present and not null.
     */
    public Task(TaskName name, Module module, Deadline deadline, Status status) {
        requireAllNonNull(name, module, deadline);
        this.name = name;
        this.module = module;
        this.deadline = deadline;
        this.status = status;
    }

    public TaskName getName() {
        return name;
    }

    public Module getModule() {
        return module;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Returns a copy of this Task with the status modified.
     *
     * @param isComplete The new completion status.
     * @return The new Task.
     */
    public Task withStatus(boolean isComplete) {
        return new Task(
                this.name,
                this.module,
                this.deadline,
                new Status(isComplete)
        );
    }

    /**
     * Returns true if the task is marked as complete.
     * @return
     */
    public boolean isTaskComplete() {
        return this.status.getIsComplete();
    }

    /**
     * Returns true if the tasks have the same name and module code.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task other) {
        if (other == this) {
            return true;
        }

        return other != null
                && other.getName().equals(this.getName())
                && other.getModule().equals(this.getModule());
    }

    /**
     * Returns true if both tasks have equal values in all fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getName().equals(getName())
                && otherTask.getModule().equals(this.getModule())
                && otherTask.getDeadline().equals(this.getDeadline())
                && otherTask.getStatus().equals(this.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.module, this.deadline, this.status);
    }

    @Override
    public String toString() {
        return this.getName()
                + "; Module: " + this.getModule()
                + "; Deadline: " + this.getDeadline()
                + "; Status: " + this.getStatus();
    }
}
