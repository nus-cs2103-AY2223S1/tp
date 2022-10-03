package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Task in the task list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    private TaskName name;
    private Module module;
    private Deadline deadline;

    public Task(TaskName name, Module module, Deadline deadline) {
        requireAllNonNull(name, module, deadline);
        this.name = name;
        this.module = module;
        this.deadline = deadline;
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
}
