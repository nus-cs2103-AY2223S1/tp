package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Student;

/**
 * Deletes a person identified using it's displayed index from profNus.
 */
public class DeleteTaCommand extends Command {

    public static final String COMMAND_WORD = "delta";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the teaching assistant identified by the index number used in the displayed "
            + " Teaching Assistant list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted TA: %1$s";

    private final Index targetIndex;

    public DeleteTaCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredTutorList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student taToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTutor(taToDelete);
        model.deletePerson(taToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, taToDelete),
                false, false, false,
                true, false, false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTaCommand) other).targetIndex)); // state check
    }
}
