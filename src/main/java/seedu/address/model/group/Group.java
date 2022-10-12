package seedu.address.model.group;

import seedu.address.model.item.AbstractContainerItem;
import seedu.address.model.item.DisplayItem;
import seedu.address.model.item.EntryType;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * Represents a Group in the address book.
 */
public class Group extends AbstractContainerItem {

    private final String groupName;
    private final Group parent;
    private final UniqueTaskList taskList;

    Group(String groupName) {
        this(groupName, null);
    }

    Group(String groupName, Group parent) {
        this.groupName = groupName;
        this.parent = parent;
        taskList = new UniqueTaskList();
    }

    /**
     * Checks if a task exists in this group
     * @param task The task to check if exists
     * @return true if it exists in this Group, false otherwise
     */
    public boolean hasTask(Task task) {
        return taskList.contains(task);
    }

    /**
     * Adds a task to this group. The task must not already exist in this group.
     *
     * @param task The task to add
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from this group. The task must already exist in this group
     * @param task The task to remove
     */
    public void removeTask(Task task) {
        taskList.remove(task);
    }

    @Override
    public EntryType getEntryType() {
        return EntryType.TEAM;
    }

    @Override
    public boolean stronglyEqual(DisplayItem o) {
        if (!weaklyEqual(o)) {
            return false;
        }
        Group g = (Group) o;
        if (parent != null) {
            return parent.equals(o);
        }
        return g.parent == null;
    }

    @Override
    public boolean weaklyEqual(DisplayItem o) {
        if (!(o instanceof Group)) {
            return false;
        }
        return ((Group) o).groupName.equals(groupName);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Group)) {
            return false;
        }
        return stronglyEqual((Group) obj);
    }

    @Override
    public boolean isPartOfContext(DisplayItem o) {
        if (parent != null) {
            return parent.equals(o);
        }
        return o == null;
    }

    @Override
    public String toString() {
        return groupName;
    }
}
