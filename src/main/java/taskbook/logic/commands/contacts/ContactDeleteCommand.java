package taskbook.logic.commands.contacts;

import static java.util.Objects.requireNonNull;
import static taskbook.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

import java.util.List;

import taskbook.commons.core.index.Index;
import taskbook.logic.commands.Command;
import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.logic.parser.CliSyntax;
import taskbook.logic.parser.contacts.ContactCategoryParser;
import taskbook.model.Model;
import taskbook.model.person.Person;


/**
 * Deletes a person identified using it's displayed index from the task book.
 */
public class ContactDeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE =
            ContactCategoryParser.CATEGORY_WORD + " " + COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "\n"
            + "Parameters: " + CliSyntax.PREFIX_INDEX + "INDEX (must be a positive integer)\n"
            + "Example: " + ContactCategoryParser.CATEGORY_WORD + " "
            + COMMAND_WORD + " " + CliSyntax.PREFIX_INDEX + "1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_PERSON_FAILURE = "Person %s cannot be deleted!\n"
        + "There are still tasks associated with the person.";

    private final Index targetIndex;

    public ContactDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());

        if (!model.canDeletePerson(personToDelete)) {
            throw new CommandException(getDeletePersonFailureMessage(personToDelete));
        }

        model.deletePerson(personToDelete);
        model.commitTaskBook();
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((ContactDeleteCommand) other).targetIndex)); // state check
    }

    /**
     * Returns failure message for failure of deletion of person.
     *
     * @param person Person to be deleted.
     * @return Failure message.
     */
    public static String getDeletePersonFailureMessage(Person person) {
        return String.format(MESSAGE_DELETE_PERSON_FAILURE, person.getName());
    }
}
