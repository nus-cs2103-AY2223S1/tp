package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Deletes all internships identified using their displayed index from the PleaseHireUs
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the internships identified by the index numbers used in the displayed internship list.\n"
            + "Parameters: INDEX... (all indexes must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 3";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Internship: \n";

    private final Set<Index> targetIndexes;

    public DeleteCommand(Set<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        UniquePersonList personsToRemove = new UniquePersonList();

        for (Index selectedIndex : targetIndexes) {
            if (selectedIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToDelete = lastShownList.get(selectedIndex.getZeroBased());
            personsToRemove.add(personToDelete);
        }

        for (Person personToDelete : personsToRemove) {
            model.deletePerson(personToDelete);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personsToRemove));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndexes.equals(((DeleteCommand) other).targetIndexes)); // state check
    }
}
