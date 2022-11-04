package seedu.hrpro.testutil;

import seedu.hrpro.model.deadline.Deadline;
import seedu.hrpro.model.task.Task;
import seedu.hrpro.model.task.TaskDescription;
import seedu.hrpro.model.task.TaskMark;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_TASK_DESCRIPTION = "Big Ben";
    public static final String DEFAULT_TASK_DEADLINE = "2023-01-01";
    public static final String DEFAULT_TASK_MARK = "false";

    private TaskDescription taskDescription;
    private Deadline taskDeadline;
    private TaskMark taskMark;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        taskDeadline = new Deadline(DEFAULT_TASK_DEADLINE);
        taskDescription = new TaskDescription(DEFAULT_TASK_DESCRIPTION);
        taskMark = new TaskMark(DEFAULT_TASK_MARK);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        taskDeadline = taskToCopy.getTaskDeadline();
        taskDescription = taskToCopy.getTaskDescription();
        taskMark = taskToCopy.getTaskMark();
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
        this.taskDeadline = new Deadline(deadline);
        return this;
    }

    /**
     * Sets the {@code TaskMark} of the {@code Task} that we are building.
     */
    public TaskBuilder withMark(String mark) {
        this.taskMark = new TaskMark(mark);
        return this;
    }

    public Task build() {
        return new Task(taskDeadline, taskDescription, taskMark);
    }

}
