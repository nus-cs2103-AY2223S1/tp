package seedu.address.logic.commands.project;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.ui.Ui;

/**
 * Lists all persons in the address book to the user.
 */
public class DeleteProjectCommand extends ProjectCommand {

    public static final String COMMAND_FLAG = "-d";

    // TODO: Better message
    public static final String MESSAGE_SUCCESS = "Deleted Project";

    // TODO: implement
    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        return null;
    }
}
