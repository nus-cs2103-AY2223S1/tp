package seedu.address.model.group;

import static seedu.address.model.AccessDisplayFlags.GROUP;

import seedu.address.model.item.AbstractSingleItem;

/**
 * Represents a Group in the address book.
 */
public class Group extends AbstractSingleItem {

    public static final String VALIDATION_REGEX = "[a-zA-Z][a-zA-Z0-9_-]*";
    public static final String MESSAGE_CONSTRAINTS = "A group name should only consist "
            + "of alphanumeric characters, underscores and hyphens only.\n";

    /**
     * Constructor to create a group object
     */
    public Group(String groupName) {
        super(groupName, GROUP, GROUP);
        assert isValidGroupName(groupName);
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
}
