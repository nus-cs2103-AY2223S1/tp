// @@author jasonchristopher21
package seedu.address.logic.commands.attributes;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.Command;

/**
 * Edits an existing attribute in the AddressBook.
 */
public abstract class EditAttributeCommand extends Command {
    public static final String COMMAND_WORD = "editfield";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits a custom field of a Person/Task/Team in the address book. "
            + "Parameters: CUSTOM_PREFIX FIELD";

    protected final String attributeName;
    protected final String attributeContent;

    /**
     * Constructs a new EditAttributeCommand instance.
     *
     * @param attributeName    The name of the attribute to be added.
     * @param attributeContent The content of the attribute to be added.
     */
    public EditAttributeCommand(String attributeName, String attributeContent) {
        requireAllNonNull(attributeName, attributeContent);
        this.attributeName = attributeName;
        this.attributeContent = attributeContent;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditAttributeCommand // instanceof handles nulls
                        && attributeName.equals(((EditAttributeCommand) other).attributeName)
                        && attributeContent.equals(((EditAttributeCommand) other).attributeContent));
    }
}
