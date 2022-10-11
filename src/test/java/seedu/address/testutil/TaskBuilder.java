package seedu.address.testutil;

import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskTitle;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {
    public static final String DEFAULT_TITLE = "This is a title";
    public static final String DEFAULT_DESCRIPTION = "This is a description";

    private TaskTitle title;
    private TaskDescription description;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        title = new TaskTitle(DEFAULT_TITLE);
        description = new TaskDescription(DEFAULT_DESCRIPTION);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        title = taskToCopy.getTitle();
        description = taskToCopy.getDescription();
    }

    /**
     * Sets the {@code TaskTitle} of the {@code Task} that we are building.
     */
    public TaskBuilder withTitle(String title) {
        this.title = new TaskTitle(title);
        return this;
    }

    /**
     * Sets the {@code TaskDescription} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.description = new TaskDescription(description);
        return this;
    }

    public Task build() {
        return new Task(title, description);
    }
}
