package seedu.address.testutil;

import java.time.LocalDate;
import java.util.Optional;

import seedu.address.model.task.TaskName;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_NAME = "Default task";
    public static final Optional<LocalDate> DEFAULT_DEADLINE = Optional.ofNullable(null);
    public static final boolean DEFAULT_IS_DONE = false;

    private TaskName taskName;
    private Optional<LocalDate> deadline;
    private boolean isDone;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        taskName = TaskName.of(DEFAULT_NAME);
        deadline = DEFAULT_DEADLINE;
        isDone = DEFAULT_IS_DONE;
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        taskName = taskToCopy.getName();
        deadline = taskToCopy.getDeadline();
        isDone = taskToCopy.getIsDone();
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.taskName = TaskName.of(name);
        return this;
    }

    /**
     * Sets the {@code deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String deadline) {
        this.deadline = SampleDataUtil.getDeadline(deadline);
        return this;
    }

    /**
     * Sets the {@code isDone} of the {@code Task} that we are building.
     */
    public TaskBuilder withIsDone(boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    public Task build() {
        return new Task(taskName, deadline.orElse(null), isDone);
    }


}
