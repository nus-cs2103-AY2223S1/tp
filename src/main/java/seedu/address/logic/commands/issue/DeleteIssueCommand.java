package seedu.address.logic.commands.issue;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.ui.Ui;

/**
 * A delete issue command to delete issues
 */
public class DeleteIssueCommand extends IssueCommand {

    public static final String COMMAND_FLAG = "-d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the issue identified by the issue id. \n"
            + "Parameters: INDEX (must be a positive integer) \n";
    // TODO: Better message
    public static final String MESSAGE_SUCCESS = "Deleted Issue: %1$s";

    // TODO: implement
    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        return null;
    }
}

