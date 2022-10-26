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
 * Adds a person attribute to the address book.
 */
public class EditPersonAttributeCommand extends EditAttributeCommand {

    public static final String MESSAGE_SUCCESS = "New field added: %s, with value: %s";

    private final Index personIndex; // change this to UUID later

    /**
     * Constructs an AddPersonAttributeCommand instance.
     * @param personIndex index of the person.
     * @param attributeName the name of the attribute to be added.
     * @param attributeContent the content of the attribute to be added.
     */
    public EditPersonAttributeCommand(Index personIndex, String attributeName, String attributeContent) {
        super(attributeName, attributeContent);
        requireNonNull(personIndex);
        this.personIndex = personIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            Person person = model.getFromFilteredPerson(personIndex);
            person.editAttribute(attributeName, attributeContent);
        } catch (PersonOutOfBoundException | AttributeException e) {
            throw new CommandException(e.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, attributeName, attributeContent));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (super.equals(other)
                && (other instanceof EditPersonAttributeCommand
                && personIndex.equals(((EditPersonAttributeCommand) other).personIndex)));
    }
}
