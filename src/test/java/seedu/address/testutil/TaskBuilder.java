package seedu.address.testutil;

import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskDescription;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_TASK_DESCRIPTION = "Big Ben";
    public static final String DEFAULT_TASK_DEADLINE = "2023-01-01";

    private TaskDescription taskDescription;
    private TaskDeadline taskDeadline;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        taskDeadline = new TaskDeadline(DEFAULT_TASK_DEADLINE);
        taskDescription = new TaskDescription(DEFAULT_TASK_DESCRIPTION);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        taskDeadline = taskToCopy.getTaskDeadline();
        taskDescription = taskToCopy.getTaskDescription();
    }

    /**
     * Sets the {@code TaskDescription} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.taskDescription = new TaskDescription(description);
        return this;
    }

    /**
     * Sets the {@code TaskDeadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String deadline) {
        this.taskDeadline = new TaskDeadline(deadline);
        return this;
    }

    public Task build() {
        return new Task(taskDeadline, taskDescription);
    }

}
