package seedu.realtime.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.realtime.commons.core.Messages;
import seedu.realtime.commons.core.index.Index;
import seedu.realtime.logic.commands.exceptions.CommandException;
import seedu.realtime.model.Model;
import seedu.realtime.model.person.Client;

/**
 * Deletes a client identified using it's displayed index from the address book.
 */
public class DeleteClientCommand extends Command {

    public static final String COMMAND_WORD = "delC";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the client identified by the index number used in the displayed client list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CLIENT_SUCCESS = "Deleted Client: %1$s";

    private final Index targetIndex;

    public DeleteClientCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteClient(clientToDelete);
        model.deleteListingsOwnedBy(clientToDelete);
        model.deleteOffersMadeBy(clientToDelete);
        model.deleteMeetingsWith(clientToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_CLIENT_SUCCESS, clientToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteClientCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteClientCommand) other).targetIndex)); // state check
    }
}
