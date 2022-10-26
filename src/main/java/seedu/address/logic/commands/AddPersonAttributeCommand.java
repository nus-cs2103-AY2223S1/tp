package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds a person to the address book.
 */
public class AddPersonAttributeCommand extends Command {

    public static final String COMMAND_WORD = "field";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a custom field to the address book. "
            + "Parameters: CUSTOM_PREFIX FIELD";

    public static final String MESSAGE_SUCCESS = "New field added: %1$s";

    private final String attributeName;
    private final String attributeContent;

    /**
     * Constructs an AddPersonAttributeCommand instance
     *
     * @param attributeName
     * @param attributeContent
     */
    public AddPersonAttributeCommand(String attributeName, String attributeContent) {
        requireAllNonNull(attributeName, attributeContent);
        this.attributeName = attributeName;
        this.attributeContent = attributeContent;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //        try {
        ////            PREFIX_FIELD.addPrefix(prefix, fieldName, model);
        //        } catch (AttributeException ae) {
        //            throw new CommandException(ae.getMessage());
        //        }
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPersonAttributeCommand // instanceof handles nulls
                && attributeName.equalsIgnoreCase(((AddPersonAttributeCommand) other).attributeName)
                && attributeContent.equals(((AddPersonAttributeCommand) other).attributeContent));
    }
}
