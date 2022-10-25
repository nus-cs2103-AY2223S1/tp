package seedu.address.model.task;

import static seedu.address.model.AccessDisplayFlags.GROUP;
import static seedu.address.model.AccessDisplayFlags.PERSON;
import static seedu.address.model.AccessDisplayFlags.TASK;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.attribute.Description;
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
    private Set<Person> assignedParents = new HashSet<>();

    /**
     * Create a new task with no completed_time
     *
     * @param title       The title of the task.
     * @param description The description of the task.
     */
    public Task(String title, String description) {
        this(title, description, null);
    }

    /**
     * Create a new task with a completed_time.
     *
     * @param title         The title of the task.
     * @param description   The description of the task.
     * @param completedTime The completed_time of the task.
     */
    public Task(String title, String description, LocalDateTime completedTime) {
        super(title, TASK, GROUP | PERSON);
        this.description = new Description(description);
        this.completedTime = completedTime;
    }

    /**
     * Marks the task if it is unmarked
     */
    public Task mark() {
        if (this.completedTime != null) {
            return this;
        }
        Task ret = new Task(name.fullName, description.getAttributeContent(), LocalDateTime.now());
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
        Task ret = new Task(name.fullName, description.getAttributeContent());
        ret.parent = parent;
        return ret;
    }

    public LocalDateTime getCompletedTime() {
        return completedTime;
    }

    /**
     * Returns true if both tasks have the same name and group. This defines a
     * weaker notion of equality between two
     * tasks.
     */
    public boolean isSameTask(Task t) {
        if (completedTime != null) {
            return name.equals(t.name) && description.equals(t.description) && completedTime.equals(t.completedTime);
        }
        return name.equals(t.name) && description.equals(t.description) && (t.completedTime == null);
    }

    /**
     * Defines a stronger notions of equality between display items.
     *
     * @param o The other item to compare equality to
     */
    @Override
    public boolean stronglyEqual(DisplayItem o) {
        return equals(o);
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
}
