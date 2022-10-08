package seedu.address.model.task;

import seedu.address.model.module.Module;

/**
 * Task class represents a task which stores the module code and the
 * description of the task.
 */
public class Task {
    private final Module module;
    private final TaskDescription description;

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
    }

    public TaskDescription getDescription() {
        return description;
    }

    public Module getModule() {
        return module;
    }

}
