package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {
    public static final String DEFAULT_TITLE = "Pay Supplier";
    public static final String DEFAULT_DEADLINE = "Oct 29 2029";

    private String title;
    private String deadline;
    private Set<Tag> tags;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        this.title = DEFAULT_TITLE;
        this.deadline = DEFAULT_DEADLINE;
        this.tags = new HashSet<>();
    }

    public Task build() {
        return new Task(title, deadline, tags);
    }
}
