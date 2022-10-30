// @@author jasonchristopher21
package seedu.address.logic.commands.attributes;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attribute.exceptions.AttributeException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonOutOfBoundException;

/**
 * Removes an existing attribute from a Person in the AddressBook.
 */
public class RemovePersonAttributeCommand extends RemoveAttributeCommand {

    public static final String MESSAGE_SUCCESS = "Field removed successfully: %s";

    private final Index personIndex; // change this to UUID later

    /**
     * Constructs an AddPersonAttributeCommand instance.
     * 
     * @param personIndex   index of the person.
     * @param attributeName the name of the attribute to be added.
     */
    public RemovePersonAttributeCommand(Index personIndex, String attributeName) {
        super(attributeName);
        requireNonNull(personIndex);
        this.personIndex = personIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            Person person = model.getFromFilteredPerson(personIndex);
            person.removeAttribute(attributeName);
        } catch (PersonOutOfBoundException | AttributeException e) {
            throw new CommandException(e.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, attributeName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (super.equals(other)
                        && (other instanceof RemovePersonAttributeCommand
                                && personIndex.equals(((RemovePersonAttributeCommand) other).personIndex)));
    }
}
