package swift.testutil;

import java.util.Optional;
import java.util.UUID;

import swift.model.task.Deadline;
import swift.model.task.Description;
import swift.model.task.Task;
import swift.model.task.TaskName;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_ID = "bfbf250c-fd58-49b4-be15-ca12095ca2ee";
    public static final String DEFAULT_TASK_NAME = "Default Task";
    public static final String DEFAULT_DESCRIPTION = "Default description";
    public static final String DEFAULT_DEADLINE = "12-12-2022 1200";

    private UUID id;
    private TaskName taskName;
    private Optional<Description> description;
    private Optional<Deadline> deadline;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        id = UUID.fromString(DEFAULT_ID);
        taskName = new TaskName(DEFAULT_TASK_NAME);
        description = Optional.of(new Description(DEFAULT_DESCRIPTION));
        deadline = Optional.of(new Deadline(DEFAULT_DEADLINE));
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        id = taskToCopy.getId();
        taskName = taskToCopy.getName();
        description = taskToCopy.getDescription();
        deadline = taskToCopy.getDeadline();
    }

    /**
     * Sets the {@code UUID} of the {@code Task} that we are building.
     */
    public TaskBuilder withId(String id) {
        this.id = UUID.fromString(id);
        return this;
    }

    /**
     * Sets the {@code TaskName} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskName(String taskName) {
        this.taskName = new TaskName(taskName);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.description = Optional.of(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String deadline) {
        this.deadline = Optional.of(new Deadline(deadline));
        return this;
    }

    public Task build() {
        return new Task(id, taskName, description, deadline);
    }
}
