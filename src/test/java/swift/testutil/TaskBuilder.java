package swift.testutil;

import swift.commons.core.index.Index;
import swift.model.task.Task;
import swift.model.task.TaskName;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_TASK_NAME = "Default Task";
    public static final int DEFAULT_INDEX = 0;

    private TaskName taskName;
    private Index index;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        taskName = new TaskName(DEFAULT_TASK_NAME);
        index = Index.fromZeroBased(DEFAULT_INDEX);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        taskName = taskToCopy.taskName;
        index = taskToCopy.contactIndex;
    }

    /**
     * Sets the {@code TaskName} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskName(String taskName) {
        this.taskName = new TaskName(taskName);
        return this;
    }

    /**
     * Sets the {@code Index} of the {@code Task} that we are building.
     */
    public TaskBuilder withContactIndex(int index) {
        this.index = Index.fromZeroBased(index);
        return this;
    }

    public Task build() {
        return new Task(taskName, index);
    }

}
