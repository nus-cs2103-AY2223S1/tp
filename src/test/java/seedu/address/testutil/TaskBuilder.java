package seedu.address.testutil;

import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskTitle;

public class TaskBuilder {
    public static final String DEFAULT_TITLE = "This is a title";
    public static final String DEFAULT_DESCRIPTION = "This is a description";

    private TaskTitle title;
    private TaskDescription description;

    public TaskBuilder() {
        title = new TaskTitle(DEFAULT_TITLE);
        description = new TaskDescription(DEFAULT_DESCRIPTION);
    }

    public TaskBuilder(Task taskToCopy) {
        title = taskToCopy.getTitle();
        description = taskToCopy.getDescription();
    }

    public TaskBuilder withTitle(String title) {
        this.title = new TaskTitle(title);
        return this;
    }

    public TaskBuilder withDescription(String description) {
        this.description = new TaskDescription(description);
        return this;
    }

    public Task build() {
        return new Task(title, description);
    }
}
