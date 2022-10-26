package seedu.address.logic.commands.client;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.ui.Ui;

public class PinClientCommand extends ClientCommand {

    public static final String COMMAND_FLAG = "-p";

    public static final String MESSAGE_SUCCESS = "Client pinned: %1$s";

    public Client toPinClient;

    public PinClientCommand(Client toPinClient) {
        this.toPinClient = toPinClient;
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
        this.toPinClient.togglePin();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toPinClient));
    }
}
