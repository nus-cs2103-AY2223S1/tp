package seedu.address.logic.commands.client;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.ui.Ui;

/**
 * TODO: Implement this to tag a client to a project
 */
public class TagClientCommand extends ClientCommand {

    public static final String COMMAND_FLAG = "-t";

    // TODO: Better message
    public static final String MESSAGE_SUCCESS = "Added Client";

    // TODO: implement
    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        return null;
    }
}
