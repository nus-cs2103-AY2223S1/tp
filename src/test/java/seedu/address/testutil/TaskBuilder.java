package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {
    public static final String DEFAULT_TITLE = "Buy Fuel";
    public static final String DEFAULT_DEADLINE = "29 Oct 2029";
    public static final boolean DEFAULT_STATUS = false;

    private String title;
    private String deadline;
    private boolean status;
    private Set<Tag> tags;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        this.title = DEFAULT_TITLE;
        this.deadline = DEFAULT_DEADLINE;
        this.status = DEFAULT_STATUS;
        this.tags = new HashSet<>();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}
     * @return
     */
    public TaskBuilder(Task task) {
        this.title = task.getTitle();
        this.deadline = task.getDeadline();
        this.status = task.getStatus();
        this.tags = new HashSet<>(task.getTags());
    }

    /**
     * Sets the title of the {@Task} that we are building.
     */
    public TaskBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Sets the deadline of the {@Task} that we are building.
     */
    public TaskBuilder withDeadline(String deadline) {
        this.deadline = deadline;
        return this;
    }

    /**
     * Sets the status of the {@Task} that we are building.
     */
    public TaskBuilder withStatus(boolean statusToSet) {
        this.status = statusToSet;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Task} that we are building.
     */
    public TaskBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Task build() {
        return new Task(title, deadline, status, tags);
    }
}
