package swift.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;

import swift.commons.core.Messages;
import swift.commons.core.index.Index;
import swift.logic.commands.exceptions.CommandException;
import swift.logic.parser.Prefix;
import swift.model.Model;
import swift.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteContactCommand extends Command {

    public static final String COMMAND_WORD = "delete_contact";
    public static final HashMap<Prefix, String> ARGUMENT_PROMPTS = new HashMap<>();

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index targetIndex;

    public DeleteContactCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteContactCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteContactCommand) other).targetIndex)); // state check
    }
}
