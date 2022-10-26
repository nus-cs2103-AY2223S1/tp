package seedu.address.testutil;

import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskTitle;
import seedu.address.model.task.ToDo;

/**
 * A utility class to help with building ToDo objects.
 */
public class ToDoBuilder {
    public static final String DEFAULT_TITLE = "This is a title";
    public static final String DEFAULT_DESCRIPTION = "This is a description";

    private TaskTitle title;
    private TaskDescription description;
    private String type;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public ToDoBuilder() {
        title = new TaskTitle(DEFAULT_TITLE);
        description = new TaskDescription(DEFAULT_DESCRIPTION);
        type = "ToDo";
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public ToDoBuilder(Task taskToCopy) {
        if (taskToCopy instanceof ToDo) {
            ToDo toDo = (ToDo) taskToCopy;
            title = toDo.getTitle();
            description = toDo.getDescription();
        }

    }

    /**
     * Sets the {@code TaskTitle} of the {@code Task} that we are building.
     */
    public ToDoBuilder withTitle(String title) {
        this.title = new TaskTitle(title);
        return this;
    }

    /**
     * Sets the {@code TaskDescription} of the {@code Task} that we are building.
     */
    public ToDoBuilder withDescription(String description) {
        this.description = new TaskDescription(description);
        return this;
    }

    public Task build() {
        return new ToDo(title, description);
    }
}
