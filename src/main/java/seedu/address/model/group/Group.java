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

    public static final String MESSAGE_CONSTRAINTS = "A group name should only consist "
            + "of alphanumeric characters, underscores and hyphens only.\n";

    public static final String VALIDATION_REGEX = "[a-zA-Z0-9_-]+";

    private final String groupName;
    private final UniqueTaskList taskList;

    /**
     * Constructs a group.
     *
     * @param groupName that will be displayed for this group.
     */
    public Group(String groupName) {
        this(groupName, null, null);
    }

    /**
     * Constructs a group.
     *
     * @param groupName that will be displayed for this group.
     * @param parent that this group will be nested under.
     * @param fullPath that leads to this group through the nesting of parent groups.
     */
    public Group(String groupName, Group parent, String fullPath) {
        super(parent, fullPath);
        this.groupName = groupName;
        taskList = new UniqueTaskList();
    }

    /**
     * Checks if the group name is valid. A group name is valid
     * if the group name is fully alphanumeric.
     *
     * @param groupName for a specific team.
     * @return true if the group name is valid, false otherwise.
     */
    public static boolean isValidGroupName(String groupName) {
        return groupName.matches(VALIDATION_REGEX);
    }

    /**
     * Gets the full path to the group inclusive of the group itself.
     *
     * @return a string indicating the full path of the group, inclusive of the group name.
     */
    public String getFullPathNameInclusive() {
        String fullPathExclusive = getFullPathName();
        if (fullPathExclusive == null) {
            return groupName;
        }

        return String.format("%s/%s", fullPathExclusive, groupName);
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
    public String toString() {
        return groupName;
    }
}
