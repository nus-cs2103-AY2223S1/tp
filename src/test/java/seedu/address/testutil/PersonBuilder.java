package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Module;
import seedu.address.model.task.Name;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Assignment 1";
    public static final String DEFAULT_MODULE = "CS2103T";
    public static final String DEFAULT_DEADLINE = "2022-12-12";

    private Name name;
    private Module module;
    private Deadline deadline;
    private Set<Tag> tags;
    private boolean isDone;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        module = new Module(DEFAULT_MODULE);
        deadline = new Deadline(DEFAULT_DEADLINE);
        tags = new HashSet<>();
        isDone = false;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code taskToCopy}.
     */
    public PersonBuilder(Task taskToCopy) {
        name = taskToCopy.getName();
        module = taskToCopy.getModule();
        deadline = taskToCopy.getDeadline();
        tags = new HashSet<>(taskToCopy.getTags());
        isDone = taskToCopy.isDone();
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Task} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code module} of the {@code Task} that we are building.
     */
    public PersonBuilder withModule(String module) {
        this.module = new Module(module);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public PersonBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    /**
     * Sets the {@code isDone} of the {@code Task} that we are building.
     */
    public PersonBuilder withIsDone(boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    public Task build() {
        return new Task(name, module, deadline, tags, isDone);
    }

}
