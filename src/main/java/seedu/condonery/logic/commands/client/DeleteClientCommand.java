package seedu.condonery.logic.commands.client;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.util.List;

import seedu.condonery.commons.core.Messages;
import seedu.condonery.commons.core.index.Index;
import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.logic.commands.exceptions.CommandException;
import seedu.condonery.model.Model;
import seedu.condonery.model.client.Client;

/**
 * Deletes a {@code Client} from Condonery.
 */
public class DeleteClientCommand extends Command {

    public static final String COMMAND_WORD = "delete -c";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete a client from Condonery.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CLIENT_SUCCESS = "Client successfully deleted: %1$s";

    private final Index targetIndex;

    /**
     * Creates an DeleteCommand to delete the specified {@code Client}
     */
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
        File image = new File(clientToDelete.getImagePath().toString());
        image.delete();
        model.deleteClient(clientToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_CLIENT_SUCCESS, clientToDelete));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteClientCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteClientCommand) other).targetIndex)); // state check
    }
}
