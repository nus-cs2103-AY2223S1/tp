package jarvis.testutil;

import java.time.LocalDate;

import jarvis.model.Task;
import jarvis.model.TaskDeadline;
import jarvis.model.TaskDesc;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_TASK_DESC = "Mark quest 1";
    public static final LocalDate DEFAULT_TASK_DEADLINE = LocalDate.parse("2022-12-12");

    private TaskDesc taskDesc;
    private TaskDeadline taskDeadline;
    private boolean isDone;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        taskDesc = new TaskDesc(DEFAULT_TASK_DESC);
        taskDeadline = new TaskDeadline(DEFAULT_TASK_DEADLINE);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        taskDesc = taskToCopy.getDesc();
        LocalDate deadline = taskToCopy.getDeadline();
        taskDeadline = new TaskDeadline(deadline);
        isDone = taskToCopy.isDone();
    }

    /**
     * Sets the {@code Desc} of the {@code Task} that we are building.
     */
    public TaskBuilder withDesc(String desc) {
        this.taskDesc = new TaskDesc(desc);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(LocalDate deadline) {
        this.taskDeadline = new TaskDeadline(deadline);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building to null.
     */
    public TaskBuilder withoutDeadline() {
        this.taskDeadline = null;
        return this;
    }

    /**
     * Returns the {@code Task} that we have built.
     */
    public Task build() {
        return new Task(taskDesc, taskDeadline);
    }

    /**
     * Returns the {@code Task} that we have built with a completion status copied from the task that TaskBuilder is
     * initiated with.
     */
    public Task buildWithCompletionStatus() {
        Task builtTask = new Task(taskDesc, taskDeadline);
        if (isDone) {
            builtTask.markAsDone();
        }
        return builtTask;
    }
}
