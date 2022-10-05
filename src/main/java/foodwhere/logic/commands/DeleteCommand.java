package foodwhere.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import foodwhere.commons.core.Messages;
import foodwhere.commons.core.index.Index;
import foodwhere.logic.commands.exceptions.CommandException;
import foodwhere.model.Model;
import foodwhere.model.stall.Stall;

/**
 * Deletes a stall identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the stall identified by the index number used in the displayed stall list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_STALL_SUCCESS = "Deleted Stall: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Stall> lastShownList = model.getFilteredStallList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
        }

        Stall stallToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteStall(stallToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_STALL_SUCCESS, stallToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
