package seedu.address.logic.commands.issue;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.issue.Issue;
import seedu.address.ui.Ui;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * A delete issue command to delete issues
 */
public class DeleteIssueCommand extends IssueCommand {

    public static final String COMMAND_FLAG = "-d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMMAND_FLAG
            + ": Deletes the issue identified by the issue id. \n"
            + "Parameters: INDEX (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + "" + COMMAND_FLAG + " 1";

    public static final String MESSAGE_SUCCESS = "Deleted Issue: %1$s";

    private final Index targetIndex;

    public DeleteIssueCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        requireNonNull(model);
        List<Issue> lastShownList = model.getFilteredIssueList();

        for (Issue i : lastShownList) {
            if (i.getIssueId().getIdInt() == targetIndex.getOneBased()) {
                Issue issueToDelete = i;
                model.deleteIssue(issueToDelete);
                return new CommandResult(String.format(MESSAGE_SUCCESS, issueToDelete));
            }
        }

        throw new CommandException(Messages.MESSAGE_INVALID_ISSUE_DISPLAYED_ID);

    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteIssueCommand
                && targetIndex.equals(((DeleteIssueCommand) other).targetIndex));
    }
}

