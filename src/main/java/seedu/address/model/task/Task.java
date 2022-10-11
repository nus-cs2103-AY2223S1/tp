package seedu.address.model.task;

import seedu.address.model.group.Group;
import seedu.address.model.item.AbstractContainerItem;
import seedu.address.model.item.DisplayItem;
import seedu.address.model.item.EntryType;
import seedu.address.model.item.exceptions.ItemCannotBeParentException;

import java.time.LocalDateTime;

public class Task implements DisplayItem {

    private final String title;
    private final String status;
    private final LocalDateTime completed_time;

    private Group parent;

    public Task(String title, String status) {
        this(title, status, null);
    }

    public Task(String title, String status, LocalDateTime completed_time) {
        this.title = title;
        this.status = status;
        this.completed_time = completed_time;
    }

    public Group getParentGroup() {
        return parent;
    }

    public boolean isSameTask(Task t) {
        return title.equals(t.title) && status.equals(t.status) && completed_time.equals(t.completed_time);
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
}
