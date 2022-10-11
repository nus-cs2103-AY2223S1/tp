package seedu.address.logic.commands.issue;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.ui.Ui;

/**
 * Command to add issue
 */
public class AddIssueCommand extends IssueCommand {

    public static final String COMMAND_FLAG = "-a";

    // TODO: Better message
    public static final String MESSAGE_SUCCESS = "Added Issue";

    // TODO: implement
    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        return null;
    }
}

