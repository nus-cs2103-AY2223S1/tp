package seedu.address.logic.commands.issue;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueId;
import seedu.address.ui.Ui;

public class PinIssueCommand extends IssueCommand {

    public static final String COMMAND_FLAG = "-p";

    public static final String MESSAGE_SUCCESS = "Issue pinned: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMMAND_FLAG
            + ": Pins the issue identified by the issue id \n"
            + "Parameters: ISSUE_ID (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " 1";

    public IssueId toPinIssueId;

    public PinIssueCommand(IssueId toPinIssueId) {
        this.toPinIssueId = toPinIssueId;
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
        Issue toPinIssue = model.getIssueById(this.toPinIssueId.getIdInt());
        toPinIssue.togglePin();
        model.sortIssuesByPin();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toPinIssue));
    }
}
