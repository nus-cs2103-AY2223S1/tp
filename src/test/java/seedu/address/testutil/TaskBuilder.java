package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.ArchivalStatus;
import seedu.address.model.task.CompletionStatus;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Id;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {
    public static final String DEFAULT_DESCRIPTION = "read book";
    public static final String DEFAULT_DEADLINE = "12-09-2022";
    public static final Boolean DEFAULT_COMPLETION_STATUS = false;
    public static final Boolean DEFAULT_ARCHIVAL_STATUS = false;
    public static final int DEFAULT_ID = 1;

    private Description description;
    private Deadline deadline;
    private CompletionStatus completionStatus;
    private ArchivalStatus archivalStatus;
    private Set<Tag> tags;
    private Id id;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        deadline = new Deadline(DEFAULT_DEADLINE);
        completionStatus = new CompletionStatus(DEFAULT_COMPLETION_STATUS);
        archivalStatus = new ArchivalStatus(DEFAULT_ARCHIVAL_STATUS);
        tags = new HashSet<>();
        id = new Id(DEFAULT_ID);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        description = taskToCopy.getDescription();
        deadline = taskToCopy.getDeadline();
        completionStatus = taskToCopy.getCompletionStatus();
        archivalStatus = taskToCopy.getArchivalStatus();

        tags = new HashSet<>(taskToCopy.getTags());
        id = taskToCopy.getId();
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Task} that we are building.
     */
    public TaskBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    /**
     * Sets the completion status of the {@code Task} that we are building.
     */
    public TaskBuilder withCompletionStatus(Boolean isDone) {
        this.completionStatus = new CompletionStatus(isDone);
        return this;
    }

    /**
     * Sets the archival status of the {@code Task} that we are building.
     */
    public TaskBuilder withArchivalStatus(Boolean isArchived) {
        this.archivalStatus = new ArchivalStatus(isArchived);
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code Task} that we are building.
     */
    public TaskBuilder withId(int id) {
        this.id = new Id(id);
        return this;
    }

    public Task build() {
        return new Task(description, deadline, completionStatus, archivalStatus, tags, id);
    }
}
