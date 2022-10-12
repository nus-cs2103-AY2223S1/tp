package seedu.address.logic.commands.client;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.ui.Ui;

/**
 * Edits the details of an existing client in the address book.
 */
public class EditClientCommand extends ClientCommand {

    public static final String COMMAND_FLAG = "-e";

    public static final String MESSAGE_SUCCESS = "Edited Client: %1$s";

    // TODO: implement

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        return null;
    }
}
