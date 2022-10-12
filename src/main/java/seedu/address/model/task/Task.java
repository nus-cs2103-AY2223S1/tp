package seedu.address.model.task;

import java.time.LocalDateTime;

import seedu.address.model.group.Group;
import seedu.address.model.item.DisplayItem;
import seedu.address.model.item.EntryType;
import seedu.address.model.item.exceptions.ItemCannotBeParentException;

/**
 * Stores task details.
 */
public class Task implements DisplayItem {

    private final String title;
    private final String status;
    private final LocalDateTime completedTime;

    private Group parent;

    /**
     * Create a new task with no completed_time
     *
     * @param title The title of the task.
     * @param status The status of the task.
     * @param group The group the task belongs to.
     */
    public Task(String title, String status, Group group) {
        this(title, status, group, null);
    }

    /**
     * Create a new task with a completed_time.
     *
     * @param title The title of the task.
     * @param status The status of the task.
     * @param group The group of the task.
     * @param completedTime The completed_time of the task.
     */
    public Task(String title, String status, Group group, LocalDateTime completedTime) {
        this.title = title;
        this.status = status;
        this.parent = group;
        this.completedTime = completedTime;
    }

    /**
     * Returns the parent {@code Group} of this Task.
     *
     * @return The parent Group.
     */
    public Group getParentGroup() {
        return parent;
    }

    /**
     * Returns true if both tasks have the same name and group. This defines a weaker notion of equality between two
     * tasks.
     */
    public boolean isSameTask(Task t) {
        return title.equals(t.title) && status.equals(t.status) && completedTime.equals(t.completedTime);
    }

    /**
     * Returns the entry type of the displayable item to determine which fxml layout card will be used to display this
     * item.
     */
    @Override
    public EntryType getEntryType() {
        return EntryType.TASK;
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
        if (!(o instanceof Group)) {
            throw new ItemCannotBeParentException(o);
        }
        parent = (Group) o;
    }

    /**
     * Returns true if {@code DisplayItem o} is a parent of this item
     *
     * @param o The item that may be a parent of this Task
     */
    @Override
    public boolean isPartOfContext(DisplayItem o) {
        return parent.equals(o);
    }

    @Override
    public String toString() {
        return "Task{" + "title: '" + title + '\'' + "; status: '" + status + '\'' + "; completedTime: " + completedTime
                + "; parent: " + parent + '}';
    }
}
