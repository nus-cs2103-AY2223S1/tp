package seedu.address.logic.commands.client;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientId;
import seedu.address.ui.Ui;

public class PinClientCommand extends ClientCommand {

    public static final String COMMAND_FLAG = "-p";

    public static final String MESSAGE_SUCCESS = "Client pinned: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMMAND_FLAG
            + ": Pins the client identified by the client id \n"
            + "Parameters: CLIENT_ID (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " 1";

    public ClientId toPinClientId;

    public PinClientCommand(ClientId toPinClientId) {
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
        Client toPinClient = model.getClientById(this.toPinClientId.getIdInt());
        toPinClient.togglePin();
        model.sortClientsByPin();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toPinClient));
    }
}
