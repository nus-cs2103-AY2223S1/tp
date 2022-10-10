package seedu.address.logic.commands.issue;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.issue.IssueCommand;
import seedu.address.model.Model;
import seedu.address.ui.Ui;

public class DeleteIssueCommand extends IssueCommand {

    public static final String COMMAND_FLAG = "-d";

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        return null;
    }
}