package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Deletes a person identified using its name from the currently displayed list of persons from Plannit.
 */
public class DeletePersonCommand extends Command {

    public static final String COMMAND_WORD = "delete-person";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by his name used in the displayed person list.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe ";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Name targetName;

    public DeletePersonCommand(Name targetName) {
        this.targetName = targetName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personToDelete = null;
        try {
            personToDelete = model.getPersonUsingName(targetName, true);
        } catch (PersonNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_NO_SUCH_PERSON_DELETE);
        }
        assert personToDelete != null;
        model.deletePerson(personToDelete);
        model.goToHomePage();
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePersonCommand // instanceof handles nulls
                && targetName.equals(((DeletePersonCommand) other).targetName)); // state check
    }
}
