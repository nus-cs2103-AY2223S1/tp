package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.model.module.Module;

/**
 * Task class represents a task which stores the module code and the
 * description of the task.
 */
public class Task {
    private final Module module;
    private final TaskDescription description;
    private final TaskStatus status;

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
        this.status = TaskStatus.INCOMPLETE;
    }

    /**
     * The constructor of the Task class. Sets the module and
     * description of the task.
     *
     * @param module The module being added.
     * @param description The description of the task.
     */
    public Task(Module module, TaskDescription description, TaskStatus status) {
        this.module = module;
        this.description = description;
        this.status = status;
    }

    public TaskDescription getDescription() {
        return description;
    }

    public Module getModule() {
        return module;
    }

    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Returns true if both tasks have the same data fields.
     */
    public boolean isSameTask(Task otherTask) {
        return this.equals(otherTask);
    }

    /**
     * Returns true if task is complete.
     */
    public boolean isComplete() {
        return this.status.isComplete();
    }

    /**
     * Marks the task as complete
     * and returns the task.
     */
    public Task mark() {
        return new Task(module, description, TaskStatus.COMPLETE);
    }

    /**
     * Unmarks (labels as incomplete) the task
     * and returns the task.
     */
    public Task unmark() {
        return new Task(module, description, TaskStatus.INCOMPLETE);
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code this}
     * edited with {@code editTaskDescriptor}.
     */
    public Task edit(EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(editTaskDescriptor);

        Module updatedModule = editTaskDescriptor.getModule().orElse(module);
        TaskDescription updatedDescription = editTaskDescriptor.getDescription().orElse(description);
        return new Task(updatedModule, updatedDescription, status);
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
