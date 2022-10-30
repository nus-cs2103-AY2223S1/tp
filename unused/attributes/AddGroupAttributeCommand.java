// @@author jasonchristopher21
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
 * Adds a group attribute to the address book.
 */
public class AddGroupAttributeCommand extends AddAttributeCommand {

    public static final String MESSAGE_SUCCESS = "New field added: %s, with value: %s";

    private final Index groupIndex; // change this to UUID later

    /**
     * Constructs an AddGroupAttributeCommand instance.
     * 
     * @param groupIndex       index of the group.
     * @param attributeName    the name of the attribute to be added.
     * @param attributeContent the content of the attribute to be added.
     */
    public AddGroupAttributeCommand(Index groupIndex, String attributeName, String attributeContent) {
        super(attributeName, attributeContent);
        requireNonNull(groupIndex);
        this.groupIndex = groupIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            Group group = model.getFromFilteredTeams(groupIndex);
            group.addAttribute(attributeName, attributeContent);
        } catch (GroupOutOfBoundException | AttributeException ae) {
            throw new CommandException(ae.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, attributeName, attributeContent));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (super.equals(other)
                        && (other instanceof AddGroupAttributeCommand
                                && groupIndex.equals(((AddGroupAttributeCommand) other).groupIndex)));
    }
}
