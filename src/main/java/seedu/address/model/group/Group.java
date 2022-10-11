package seedu.address.model.group;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.item.AbstractContainerItem;
import seedu.address.model.item.DisplayItem;
import seedu.address.model.item.EntryType;

/**
 * Represents a Group in the address book.
 */
public class Group extends AbstractContainerItem {

    public static final String MESSAGE_CONSTRAINTS = "A group name should only consist "
            + "of alphanumeric characters and should not include whitespaces or "
            + "slashes.\n";

    public static final String VALIDATION_REGEX = "[{Alnum}]*";

    private final String groupName;
    private final Group parent;

    Group(String groupName) {
        this(groupName, null);
    }

    Group(String groupName, String fullPath, Group parent) {
        super(parent);
        this.groupName = groupName;
        this.parent = parent;
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
