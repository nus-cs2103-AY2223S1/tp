package seedu.address.logic.commands.client;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.ui.Ui;

/**
 * Deletes a client identified using it's displayed index from the address book.
 */
public class DeleteClientCommand extends ClientCommand {

    public static final String COMMAND_FLAG = "-d";

    public static final String MESSAGE_SUCCESS = "Deleted Client: %1$s";

    // TODO: implementation
    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        return null;
    }
}
