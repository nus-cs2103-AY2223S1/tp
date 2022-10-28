package seedu.address.model.task;

import static seedu.address.model.AccessDisplayFlags.GROUP;
import static seedu.address.model.AccessDisplayFlags.PERSON;
import static seedu.address.model.AccessDisplayFlags.TASK;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.model.attribute.Description;
import seedu.address.model.attribute.Name;
import seedu.address.model.item.AbstractDisplayItem;
import seedu.address.model.item.AbstractSingleItem;
import seedu.address.model.item.DisplayItem;
import seedu.address.model.item.exceptions.ItemCannotBeParentException;
import seedu.address.model.person.Person;

/**
 * Stores task details.
 */
public class Task extends AbstractSingleItem {

    private final Description description;
    private final LocalDateTime completedTime;
    private String progress;
    private Set<Person> assignedParents = new HashSet<>();

    /**
     * Create a new task with no completed_time
     *
     * @param title       The title of the task.
     * @param description The description of the task.
     */
    public Task(Name title, String description, String progress) {
        this(title, description, "0%", null);
    }

    /**
     * Create a new task with a completed_time.
     *
     * @param title         The title of the task.
     * @param description   The description of the task.
     * @param completedTime The completed_time of the task.
     */
    public Task(Name title, String description, String progress, LocalDateTime completedTime) {
        super(title, TASK, GROUP | PERSON);
        this.description = new Description(description);
        this.progress = progress;
        this.completedTime = completedTime;
    }

    /**
     * Marks the task if it is unmarked
     */
    public Task mark() {
        if (this.completedTime != null) {
            return this;
        }
        Task ret = new Task(name, description.getAttributeContent(), "100%", LocalDateTime.now());
        ret.parent = parent;
        return ret;
    }

    /**
     * Unmarks the task if it is marked
     */
    public Task unmark() {
        if (this.completedTime == null) {
            return this;
        }
        Task ret = new Task(name, this.progress, description.getAttributeContent());
        ret.parent = parent;
        return ret;
    }

    public LocalDateTime getCompletedTime() {
        return completedTime;
    }

    /**
     * Returns the content of the task description.
     *
     * @return content of task description, or an empty string if it does not exist.
     */
    public String getDescriptionContent() {
        if (description != null) {
            return description.getAttributeContent();
        }
        return "";
    }

    /**
     * Returns true if both tasks have the same name and group. This defines a
     * weaker notion of equality between two
     * tasks.
     */
    public boolean isSameTask(Task t) {
        return getFullPath().equals(t.getFullPath());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof DisplayItem) {
            return this.stronglyEqual((DisplayItem) o);
        }
        return false;
    }

    /**
     * Defines a stronger notions of equality between display items.
     *
     * @param o The other item to compare equality to
     */
    @Override
    public boolean stronglyEqual(DisplayItem o) {
        if (!weaklyEqual(o)) {
            return false;
        }
        Task task = (Task) o;
        return completedTime.equals(task.completedTime) && description.equals(task.description)
                && getAttributes().equals(task.getAttributes());
    }

    /**
     * Defines a weaker notion of equality between display items.
     *
     * @param o The other item to compare equality to
     */
    @Override
    public boolean weaklyEqual(DisplayItem o) {
        if (o instanceof Task) {
            return isSameTask((Task) o);
        }
        return false;
    }

    public Task setProgress(String level) {
        if (this.completedTime != null) {
            return this;
        }

        Task editedTask = new Task(name, description.getAttributeContent(), level, LocalDateTime.now());
        return editedTask;
    }

    /**
     * Make the current item to belong under {@code DisplayItem o}
     *
     * @param o The new parent for the Task
     */
    @Override
    public void setParent(DisplayItem o) throws ItemCannotBeParentException {
        if (o == null) {
            parent = null;
            return;
        }
        assert o instanceof AbstractDisplayItem;

        if (!canBeChildOf((AbstractDisplayItem) o)) {
            throw new ItemCannotBeParentException(o);
        }

        if (o instanceof AbstractSingleItem) {
            setParentForSingleGrp((AbstractSingleItem) o);
        }

        if (o instanceof Person) {
            setContactParent((Person) o);
        }

    }

    @Override
    public Set<? extends DisplayItem> getParents() {
        return Stream.concat(super.getParents().stream(), assignedParents.stream()).collect(Collectors.toSet());
    }

    private void setParentForSingleGrp(AbstractSingleItem o) {
        super.setParent(o);
    }

    private void setContactParent(Person o) {
        if (o == null) {
            return;
        }
        if (!assignedParents.contains(o)) {
            throw new ItemCannotBeParentException(o);
        }


        assignedParents.add(o);
    }

    @Override
    public Name getName() {
        return null;
    }

    @Override
    public String toString() {
        return name.toString();
    }

    @Override
    public UUID getUuid() {
        return UUID.nameUUIDFromBytes(("Task: " + getFullPath()).getBytes(StandardCharsets.UTF_8));
    }

}
