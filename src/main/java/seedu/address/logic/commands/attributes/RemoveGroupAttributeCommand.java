package seedu.address.logic.commands.attributes;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attribute.exceptions.AttributeException;
import seedu.address.model.group.Group;
import seedu.address.model.group.exceptions.GroupOutOfBoundException;

/**
 * Removes an existing attribute from a Person in the AddressBook.
 */
public class RemoveGroupAttributeCommand extends RemoveAttributeCommand {

    public static final String MESSAGE_SUCCESS = "Field removed successfully: %s";

    private final Index groupIndex; // change this to UUID later

    /**
     * Constructs an RemoveGroupAttributeCommand instance.
     * @param groupIndex index of the person.
     * @param attributeName the name of the attribute to be added.
     */
    public RemoveGroupAttributeCommand(Index groupIndex, String attributeName) {
        super(attributeName);
        requireNonNull(groupIndex);
        this.groupIndex = groupIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            Group group = model.getFromFilteredTeams(groupIndex);
            group.removeAttribute(attributeName);
        } catch (GroupOutOfBoundException | AttributeException e) {
            throw new CommandException(e.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, attributeName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (super.equals(other)
                && (other instanceof RemoveGroupAttributeCommand
                && groupIndex.equals(((RemoveGroupAttributeCommand) other).groupIndex)));
    }
}
