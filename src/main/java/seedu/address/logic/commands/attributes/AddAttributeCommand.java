package seedu.address.logic.commands.attributes;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.Command;

/**
 * Adds an attribute to a person.
 */
public abstract class AddAttributeCommand extends Command {

    public static final String COMMAND_WORD = "addfield";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a custom field to the address book. "
            + "Parameters: CUSTOM_PREFIX FIELD";

    protected final String attributeName;
    protected final String attributeContent;

    /**
     * Constructs a new AddAttributeCommand instance.
     *
     * @param attributeName The name of the attribute to be added.
     * @param attributeContent The content of the attribute to be added.
     */
    public AddAttributeCommand(String attributeName, String attributeContent) {
        requireAllNonNull(attributeName, attributeContent);
        this.attributeName = attributeName;
        this.attributeContent = attributeContent;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAttributeCommand // instanceof handles nulls
                && attributeName.equals(((AddAttributeCommand) other).attributeName)
                && attributeContent.equals(((AddAttributeCommand) other).attributeContent));
    }
}
