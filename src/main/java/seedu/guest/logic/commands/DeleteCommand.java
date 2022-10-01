package seedu.guest.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.guest.commons.core.Messages;
import seedu.guest.commons.core.index.Index;
import seedu.guest.logic.commands.exceptions.CommandException;
import seedu.guest.model.Model;
import seedu.guest.model.guest.Guest;

/**
 * Deletes a guest identified using it's displayed index from the guest book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the guest identified by the index number used in the displayed guest book.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_GUEST_SUCCESS = "Deleted Guest: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Guest> lastShownList = model.getFilteredGuestList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GUEST_DISPLAYED_INDEX);
        }

        Guest guestToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteGuest(guestToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_GUEST_SUCCESS, guestToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
