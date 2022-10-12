package seedu.address.model.task;

import java.time.LocalDateTime;

import seedu.address.model.item.AbstractContainerItem;
import seedu.address.model.item.DisplayItem;
import seedu.address.model.item.EntryType;
import seedu.address.model.item.exceptions.ItemCannotBeParentException;

/**
 * Stores task details.
 */
public class Task implements DisplayItem {

    private final String title;
    private final String description;
    private final LocalDateTime completedTime;

    private AbstractContainerItem parent;

    /**
     * Create a new task with no completed_time
     *
     * @param title  The title of the task.
     * @param status The status of the task.
     */
    public Task(String title, String status) {
        this(title, status, null);
    }

    /**
     * Create a new task with a completed_time.
     *
     * @param title         The title of the task.
     * @param status        The status of the task.
     * @param completedTime The completed_time of the task.
     */
    public Task(String title, String status, LocalDateTime completedTime) {
        this.title = title;
        this.description = status;
        this.completedTime = completedTime;
    }

    /**
     * Marks the task if it is unmarked
     */
    public Task mark() {
        if (this.completedTime != null) {
            return this;
        }
        Task ret = new Task(title, description, LocalDateTime.now());
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
        Task ret = new Task(title, description);
        ret.parent = parent;
        return ret;
    }

    public String getStatus() {
        return description;
    }

    public LocalDateTime getCompletedTime() {
        return completedTime;
    }

    /**
     * Returns the parent {@code Group} of this Task.
     *
     * @return The parent Group.
     */
    public AbstractContainerItem getParentGroup() {
        return parent;
    }

    /**
     * Returns true if both tasks have the same name and group. This defines a
     * weaker notion of equality between two
     * tasks.
     */
    public boolean isSameTask(Task t) {
        if (completedTime != null) {
            return title.equals(t.title) && description.equals(t.description) && completedTime.equals(t.completedTime);
        }
        return title.equals(t.title) && description.equals(t.description) && (t.completedTime == null);
    }

    /**
     * Returns the entry type of the displayable item to determine which fxml layout
     * card will be used to display this
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
        if (o == null) {
            parent = null;
            return;
        }
        if (!(o instanceof AbstractContainerItem)) {
            throw new ItemCannotBeParentException(o);
        }
        parent = (AbstractContainerItem) o;
    }

    public String getParentPath() {
        return parent.getFullPathName();
    }

    /**
     * Returns true if {@code DisplayItem o} is a parent of this item
     *
     * @param o The item that may be a parent of this Task
     */
    @Override
    public boolean isPartOfContext(DisplayItem o) {
        if (o == null) {
            return true;
        }
        AbstractContainerItem tmp = parent;
        while (tmp != null) {
            if (tmp.equals(o)) {
                return true;
            }
            tmp = tmp.getParent();
        }
        return false;
    }

    @Override
    public String toString() {
        return title;
    }
}
