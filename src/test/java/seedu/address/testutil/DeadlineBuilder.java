package seedu.address.testutil;

import seedu.address.model.task.Deadline;
import seedu.address.model.task.FormatDate;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskTitle;

/**
 * A utility class to help with building Deadline objects.
 */
public class DeadlineBuilder {
    public static final String DEFAULT_TITLE = "This is a title";
    public static final String DEFAULT_DESCRIPTION = "This is a description";
    public static final String DEFAULT_DATE = "2020-12-12";

    private TaskTitle title;
    private TaskDescription description;
    private FormatDate date;
    private String type;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public DeadlineBuilder() {
        title = new TaskTitle(DEFAULT_TITLE);
        description = new TaskDescription(DEFAULT_DESCRIPTION);
        date = new FormatDate(DEFAULT_DATE);
        type = "Deadline";
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public DeadlineBuilder(Task taskToCopy) {
        if (taskToCopy instanceof Deadline) {
            Deadline deadline = (Deadline) taskToCopy;
            title = deadline.getTitle();
            description = deadline.getDescription();
            date = deadline.getDate();
        }

    }

    /**
     * Sets the {@code TaskTitle} of the {@code Task} that we are building.
     */
    public DeadlineBuilder withTitle(String title) {
        this.title = new TaskTitle(title);
        return this;
    }

    /**
     * Sets the {@code TaskDescription} of the {@code Task} that we are building.
     */
    public DeadlineBuilder withDescription(String description) {
        this.description = new TaskDescription(description);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Task} that we are building.
     */
    public DeadlineBuilder withDate(String date) {
        this.date = new FormatDate(date);
        return this;
    }

    public Task build() {
        return new Deadline(title, description, date);
    }
}
