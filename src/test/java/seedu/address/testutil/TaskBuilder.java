package seedu.address.testutil;

import seedu.address.model.exam.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskStatus;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_DESCRIPTION = "Assignment 1";
    public static final String DEFAULT_MODULE = "CS2103T";

    private Module module;
    private TaskDescription description;
    private TaskStatus status;
    private Exam exam;
    private Tag tag;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        module = new Module(new ModuleCode(DEFAULT_MODULE));
        description = new TaskDescription(DEFAULT_DESCRIPTION);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        description = taskToCopy.getDescription();
        module = taskToCopy.getModule();
    }

    /**
     * Sets the {@code TaskDescription} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.description = new TaskDescription(description);
        return this;
    }

    /**
     * Sets the {@code Module} of the {@code Task} that we are building.
     */
    public TaskBuilder withModule(String module) {
        this.module = new Module(new ModuleCode(module));
        return this;
    }

    /**
     * Sets the {@code TaskStatus} of the {@code Task} that we are building.
     */
    public TaskBuilder withStatus(String complete) {
        this.status = TaskStatus.of(complete);
        return this;
    }

    public Task build() {
        return new Task(module, description, status);
    }

}
