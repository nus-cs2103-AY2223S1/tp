package seedu.address.model.group;


import seedu.address.model.attribute.Name;


import java.nio.charset.StandardCharsets;
import java.util.UUID;

import seedu.address.model.item.AbstractSingleItem;


/**
 * Represents a Group in the address book.
 */
public class Group extends AbstractSingleItem {

    public static final String VALIDATION_REGEX = "[a-zA-Z][a-zA-Z0-9_-]*";
    public static final String MESSAGE_CONSTRAINTS = "A group name should only consist "
            + "of alphanumeric characters, underscores and hyphens only.\n";

    public Group(Name groupName) {
        this(groupName, null);
    }

    public Group(Name groupName, Group parent) {
        super(groupName, parent.getTypeFlag(), parent);

    }

    /*
     * Checks if the group name is valid. A group name is valid
     * if the group name is fully alphanumeric.
     *
     * @param groupName for a specific team.
     * @return true if the group name is valid, false otherwise.
     */
    //public static boolean isValidGroupName(Name groupName) {
    //    return groupName.matches(VALIDATION_REGEX);
    //}

    @Override
    public UUID getUid() {
        return UUID.nameUUIDFromBytes(("Group: " + getFullPath()).getBytes(StandardCharsets.UTF_8));
    }

}
