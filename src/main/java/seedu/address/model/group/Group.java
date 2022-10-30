package seedu.address.model.group;

import static seedu.address.model.AccessDisplayFlags.GROUP;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import seedu.address.model.attribute.Name;
import seedu.address.model.item.AbstractSingleItem;
import seedu.address.model.item.DisplayItem;
import seedu.address.model.item.EntryType;

/**
 * Represents a Group in the address book.
 */
public class Group extends AbstractSingleItem {

    // public static final String VALIDATION_REGEX = "[a-zA-Z][a-zA-Z0-9_-]*";
    public static final String VALIDATION_REGEX = "[a-zA-Z][a-zA-Z0-9]*";
    public static final String MESSAGE_CONSTRAINTS = "A group name should only consist "
            + "of alphanumeric characters only and start with a letter.\n";

    /**
     * Constructor to create a group object
     */
    public Group(Name groupName) {
        super(groupName, GROUP, GROUP);
        assert isValidGroupName(String.valueOf(groupName));
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
            return parent.equals(g.parent);
        }
        return g.parent == null;
    }

    @Override
    public boolean weaklyEqual(DisplayItem o) {
        if (!(o instanceof Group)) {
            return false;
        }
        return ((Group) o).name.equals(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Group)) {
            return false;
        }
        return stronglyEqual((Group) obj);
    }

    @Override
    public boolean isPartOfContext(DisplayItem o) {
        if (o == null) {
            return true;
        }

        AbstractSingleItem tmp = parent;
        while (tmp != null) {
            if (tmp.equals(o)) {
                return true;
            }
            tmp = tmp.getParent();
        }
        return false;
    }


    @Override
    public UUID getUuid() {
        return UUID.nameUUIDFromBytes(("Group: " + getFullPath()).getBytes(StandardCharsets.UTF_8));
    }

}
