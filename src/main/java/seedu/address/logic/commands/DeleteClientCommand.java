package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;

/**
 * Deletes a client identified using it's displayed index from MyInsuRec.
 */
public class DeleteClientCommand extends Command {

    public static final String COMMAND_WORD = "delClient";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the client identified by their index number.\n"
            + "Parameters: " + PREFIX_INDEX + "INDEX\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_INDEX + "1";

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
        return new CommandResult(String.format(MESSAGE_DELETE_CLIENT_SUCCESS, clientToDelete), CommandSpecific.CLIENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteClientCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteClientCommand) other).targetIndex)); // state check
    }
}
