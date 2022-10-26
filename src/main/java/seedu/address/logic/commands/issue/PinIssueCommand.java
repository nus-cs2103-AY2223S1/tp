package seedu.address.logic.commands.issue;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.issue.Issue;
import seedu.address.ui.Ui;

public class PinIssueCommand extends IssueCommand {

    public static final String COMMAND_FLAG = "-p";

    public static final String MESSAGE_SUCCESS = "Issue pinned: %1$s";

    public Issue toPinIssue;

    public PinIssueCommand(Issue toPinIssue) {
        this.toPinIssue = toPinIssue;
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
        this.toPinIssue.togglePin();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toPinIssue));
    }
}
