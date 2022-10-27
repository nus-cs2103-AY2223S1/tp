package seedu.address.logic.commands.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientId;
import seedu.address.ui.Ui;

/**
 * Encapsulates a command to pin a client entity.
 */
public class PinClientCommand extends ClientCommand {

    public static final String COMMAND_FLAG = "-p";

    public static final String MESSAGE_PIN_SUCCESS = "Client pinned: %1$s";
    public static final String MESSAGE_UNPIN_SUCCESS = "Client unpinned: %1$s";
    public static final String MESSAGE_CLIENT_NOT_FOUND = "This client id does not exist.";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMMAND_FLAG
            + ": Pins the client identified by the client id \n"
            + "Parameters: CLIENT_ID (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " 1";

    private ClientId toPinClientId;

    /**
     * Constructor for a command to pin clients.
     *
     * @param toPinClientId The ID of the client to be pinned.
     */
    public PinClientCommand(ClientId toPinClientId) {
        requireNonNull(toPinClientId);
        this.toPinClientId = toPinClientId;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @param ui
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        if (!model.hasClientId(this.toPinClientId.getIdInt())) {
            throw new CommandException(MESSAGE_CLIENT_NOT_FOUND);
        }
        Client toPinClient = model.getClientById(this.toPinClientId.getIdInt());
        toPinClient.togglePin();
        model.sortClientsByPin();
        ui.showClients();
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        return new CommandResult(String.format(
                toPinClient.isPinned() ? MESSAGE_PIN_SUCCESS : MESSAGE_UNPIN_SUCCESS,
                toPinClient));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PinClientCommand)) {
            return false;
        }

        return this.toPinClientId.equals(((PinClientCommand) other).toPinClientId);
    }
}
