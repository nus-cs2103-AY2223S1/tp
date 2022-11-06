package modtrekt.testutil;

import modtrekt.model.module.ModCode;
import modtrekt.model.task.Description;
import modtrekt.model.task.Task;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {
    public static final String DEFAULT_DESC = "Complete Assignment";
    public static final String DEFAULT_MOD_CODE = "CS2103T";
    public static final Task.Priority DEFAULT_PRIORITY = Task.Priority.NONE;

    private Description description;
    private ModCode modCode;
    private boolean isDone;
    private Task.Priority priority;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        description = new Description(DEFAULT_DESC);
        modCode = new ModCode(DEFAULT_MOD_CODE);
        priority = DEFAULT_PRIORITY;
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        description = taskToCopy.getDescription();
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String desc) {
        this.description = new Description(desc);
        return this;
    }

    /**
     * Sets the {@code ModCode} of the {@code Task} that we are building.
     */
    public TaskBuilder withModCode(String code) {
        this.modCode = new ModCode(code);
        return this;
    }

    /**
     * Sets the {@code isDone} of the {@code Task} that we are building.
     */
    public TaskBuilder withIsDone(boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    /**
     * Sets the {@code priority} of the {@code Task} that we are building.
     */
    public TaskBuilder withPriority(Task.Priority priority) {
        this.priority = priority;
        return this;
    }

    public Task build() {
        return new Task(description, modCode, isDone, priority);
    }
}
