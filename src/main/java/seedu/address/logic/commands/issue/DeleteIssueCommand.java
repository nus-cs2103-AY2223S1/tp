package seedu.address.logic.commands.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ISSUES;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.issue.Issue;
import seedu.address.ui.Ui;

/**
 * A delete issue command to delete issues
 */
public class DeleteIssueCommand extends IssueCommand {

    public static final String COMMAND_FLAG = "-d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMMAND_FLAG
            + ": Deletes the issue by its id. Id must be positive and valid \n"
            + "Parameters: ISSUE_ID \n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_FLAG + " 1";

    public static final String MESSAGE_SUCCESS = "Deleted Issue: %1$s";

    private final Index targetIndex;

    public DeleteIssueCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        requireNonNull(model);
        ui.showIssues();
        List<Issue> lastShownList = model.getFilteredIssueList();

        for (Issue i : lastShownList) {
            if (i.getIssueIdInInt() == targetIndex.getOneBased()) {
                i.deleteProjectIssue(i);
                model.deleteIssue(i);
                model.updateFilteredIssueList(PREDICATE_SHOW_ALL_ISSUES);
                return new CommandResult(String.format(MESSAGE_SUCCESS, i));
            }
        }

        throw new CommandException(Messages.MESSAGE_ISSUE_NOT_FOUND);

    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteIssueCommand
                && targetIndex.equals(((DeleteIssueCommand) other).targetIndex));
    }
}

