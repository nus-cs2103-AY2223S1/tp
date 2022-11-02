package foodwhere.logic.commands;

import static foodwhere.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static foodwhere.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static java.util.Objects.requireNonNull;

import java.util.List;

import foodwhere.commons.core.index.Index;
import foodwhere.logic.commands.exceptions.CommandException;
import foodwhere.model.Model;
import foodwhere.model.stall.Stall;

/**
 * Deletes a stall identified using it's displayed index from the address book.
 */
public class SDeleteCommand extends Command {

    public static final String COMMAND_WORD = "sdel";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the stall identified by the index number used in the displayed stall list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_STALL_SUCCESS = "Deleted Stall: %1$s";

    public static final String MESSAGE_INVALID_INDEX_ERROR =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_INDEX)
                    + SDeleteCommand.MESSAGE_USAGE;

    private final Index targetIndex;

    public SDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Stall> lastShownList = model.getFilteredStallList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_INDEX_ERROR);
        }

        Stall stallToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteStall(stallToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_STALL_SUCCESS, stallToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((SDeleteCommand) other).targetIndex)); // state check
    }
}
