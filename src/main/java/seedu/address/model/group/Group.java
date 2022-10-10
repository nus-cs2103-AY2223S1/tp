package seedu.address.model.group;

import seedu.address.model.item.AbstractContainerItem;
import seedu.address.model.item.DisplayItem;
import seedu.address.model.item.EntryType;

/**
 * Represents a Group in the address book.
 */
public class Group extends AbstractContainerItem {

    private final String groupName;
    private final Group parent;

    Group(String groupName) {
        this(groupName, null);
    }

    Group(String groupName, Group parent) {
        this.groupName = groupName;
        this.parent = parent;
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
