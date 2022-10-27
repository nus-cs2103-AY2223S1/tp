package seedu.address.logic.commands.attributes;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;

/**
 * Removes an attribute from the AddressBook.
 */
public abstract class RemoveAttributeCommand extends Command {

    public static final String COMMAND_WORD = "rmfield";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a custom field from the address book. "
            + "Parameters: CUSTOM_PREFIX FIELD";

    protected final String attributeName;

    /**
     * Constructs a new RemoveAttributeCommand instance.
     *
     * @param attributeName The name of the attribute to be removed.
     */
    public RemoveAttributeCommand(String attributeName) {
        requireNonNull(attributeName);
        this.attributeName = attributeName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveAttributeCommand // instanceof handles nulls
                && attributeName.equals(((RemoveAttributeCommand) other).attributeName));
    }
}
