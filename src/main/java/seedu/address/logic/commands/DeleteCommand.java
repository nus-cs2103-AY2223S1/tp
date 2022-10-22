package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.storage.ClassStorage;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX [OPTIONAL INDEXES] (must be positive integer(s))\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deletion successful!";
    public static final String NO_PERSON_TO_DELETE_ERROR = "There is no student to delete.";

    private final List<Index> targetIndexes;

    /**
     * Creates a DeleteCommand to delete the specified {@code Person}(s).
     *
     * @param targetIndexes of the person(s) to delete.
     */
    public DeleteCommand(List<Index> targetIndexes) {
        Collections.sort(targetIndexes);
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (model.getFilteredPersonList().size() == 0) {
            throw new CommandException(NO_PERSON_TO_DELETE_ERROR);
        }

        for (int i = targetIndexes.size() - 1; i >= 0; i--) {
            Index targetIndex = targetIndexes.get(i);
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deletePerson(personToDelete);
            ClassStorage.removeExistingClass(personToDelete);
        }
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndexes.equals(((DeleteCommand) other).targetIndexes)); // state check
    }
}
