package seedu.rc4hdb.logic.commands.residentcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.rc4hdb.commons.core.Messages;
import seedu.rc4hdb.commons.core.index.Index;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.ModelCommand;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.resident.Resident;

/**
 * Deletes a resident identified using it's displayed index from the resident book.
 */
public class DeleteCommand implements ModelCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the resident identified by the index number used in the displayed resident list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_RESIDENT_SUCCESS = "Deleted Resident: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Resident> lastShownList = model.getFilteredResidentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_NO_RESIDENT_WITH_INDEX);
        }

        Resident residentToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteResident(residentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_RESIDENT_SUCCESS, residentToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
