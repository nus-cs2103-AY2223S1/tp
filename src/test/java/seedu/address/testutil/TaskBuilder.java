package seedu.address.testutil;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.attribute.Attribute;
import seedu.address.model.attribute.Description;
import seedu.address.model.attribute.Name;
import seedu.address.model.item.AbstractDisplayItem;
import seedu.address.model.item.AbstractSingleItem;
import seedu.address.model.item.DisplayItem;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder extends AbstractSingleItemBuilder {

    public static final String DEFAULT_NAME = "Make your bed";
    public static final String DEFAULT_DESCRIPTION = "Making your bed is important!";

    private Description description;
    private LocalDateTime completedTime;
    private Set<Person> assignedParents;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        super(new Name(DEFAULT_NAME), new ArrayList<>(), new HashSet<>());
        this.description = new Description(DEFAULT_DESCRIPTION);
        this.assignedParents = new HashSet<>();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        super(taskToCopy.getName(), new ArrayList<>(taskToCopy.getAttributes()),
                new HashSet<>(taskToCopy.getTags()));
        this.description = taskToCopy.getDescription();
        this.completedTime = taskToCopy.getCompletedTime();
        taskToCopy.getParents().forEach(parent -> withParent(parent));
    }

    /**
     * Sets the parent of the {@code TaskBuilder} that is being built.
     */
    public TaskBuilder withParent(DisplayItem parent) {
        requireNonNull(parent);
        assert parent instanceof AbstractDisplayItem;
        if (parent instanceof AbstractSingleItem) {
            this.parent = (AbstractSingleItem) parent;
        }

        if (parent instanceof Person) {
            assignedParents.add((Person) parent);
        }
        return this;
    }

    @Override
    public TaskBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    @Override
    public TaskBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    @Override
    public TaskBuilder withAttribute(Attribute<?> attribute) {
        super.addAttribute(attribute);
        return this;
    }

    @Override
    public <U> TaskBuilder withAttribute(String name, U data) {
        super.addAttribute(name, data);
        return this;
    }

    /**
     * Sets the description of the {@code TaskBuilder} that is being built.
     */
    public TaskBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the completed time of the {@code TaskBuilder} that is being built.
     */
    public TaskBuilder withCompletedTime(LocalDateTime completedTime) {
        this.completedTime = completedTime;
        return this;
    }

    @Override
    public Task build() {
        Task task = new Task(name.fullName, description.getAttributeContent(), completedTime);
        for (Person parent : assignedParents) {
            task.setParent(parent);
        }

        if (parent != null) {
            task.setParent(parent);
        }

        for (Attribute<?> attr : attributes) {
            task.addAttribute(attr);
        }

        return task;
    }
}
