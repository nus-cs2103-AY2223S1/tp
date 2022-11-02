package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes one or more persons identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes person(s) identified by each given index number, with respect to the displayed contact list.\n"
            + "Parameters: INDEX1 [INDEX2]... (all must be positive integers)\n"
            + "Example: " + COMMAND_WORD + " 1, " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person(s):\n";
    public static final String MESSAGE_FORMAT_SPECIFIER = "%1$s";

    public final Set<Index> targetIndexSet;

    public DeleteCommand(Set<Index> targetIndexSet) {
        this.targetIndexSet = targetIndexSet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        String deleteMessage = MESSAGE_DELETE_PERSON_SUCCESS;
        for (Index targetIndex : targetIndexSet) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deletePerson(personToDelete);
            model.updatePieChart();
            deleteMessage += personToDelete + "\n";
        }
        return new CommandResult(deleteMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && this.targetIndexSet.equals(((DeleteCommand) other).targetIndexSet)); // state check
    }
}
