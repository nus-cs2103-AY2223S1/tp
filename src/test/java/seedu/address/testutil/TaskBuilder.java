package seedu.address.testutil;

import seedu.address.model.module.Module;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_NAME = "Tutorial 3";
    public static final String DEFAULT_MODULE = "CS1231S";
    public static final String DEFAULT_DEADLINE = "2022-12-12 23:59";

    private TaskName name;
    private Module module;
    private Deadline deadline;
    private Status status;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        name = new TaskName(DEFAULT_NAME);
        module = new Module(DEFAULT_MODULE);
        deadline = new Deadline(DEFAULT_DEADLINE);
        status = new Status(false);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        name = taskToCopy.getName();
        module = taskToCopy.getModule();
        deadline = taskToCopy.getDeadline();
        status = taskToCopy.getStatus();
    }

    /**
     * Sets the {@code TaskName} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.name = new TaskName(name);
        return this;
    }

    /**
     * Sets the {@code Module} of the {@code Task} that we are building.
     */
    public TaskBuilder withModule(String module) {
        this.module = new Module(module);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    public Task build() {
        return new Task(name, module, deadline, status);
    }
}
