package tuthub.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import tuthub.commons.core.Messages;
import tuthub.commons.core.index.Index;
import tuthub.logic.commands.exceptions.CommandException;
import tuthub.model.Model;
import tuthub.model.tutor.Tutor;

/**
 * Deletes a tutor identified using it's displayed index from tuthub.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the tutor identified by the index number used in the displayed tutor list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TUTOR_SUCCESS = "Deleted Tutor: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tutor> lastShownList = model.getFilteredTutorList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX);
        }

        Tutor tutorToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTutor(tutorToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TUTOR_SUCCESS, tutorToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
