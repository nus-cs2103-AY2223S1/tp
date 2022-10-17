package swift.testutil;

import java.util.UUID;

import swift.model.task.Task;
import swift.model.task.TaskName;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_ID = "dccb909c-216d-42f8-b1e2-0e5779a1d501";
    public static final String DEFAULT_TASK_NAME = "Default Task";

    private UUID id;
    private TaskName taskName;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        id = UUID.fromString(DEFAULT_ID);
        taskName = new TaskName(DEFAULT_TASK_NAME);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        id = taskToCopy.getId();
        taskName = taskToCopy.taskName;
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

    public Task build() {
        return new Task(id, taskName);
    }

}
