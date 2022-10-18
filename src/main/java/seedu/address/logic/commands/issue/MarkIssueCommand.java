package seedu.address.logic.commands.issue;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.Status;
import seedu.address.ui.Ui;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ISSUES;

/**
 * Command to mark an issue as complete or incomplete
 */
public class MarkIssueCommand extends IssueCommand {

    public static final String COMMAND_FLAG = "-m";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMMAND_FLAG
            + ": Marks the issue identified by the issue id " +
            "as complete if it is currently incomplete, or incomplete if it is currently completed"
            + "Parameters: ISSUE_ID (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " 1";


    public static final String MESSAGE_SUCCESS = "Issue marked: %1$s";

    private final Index targetIndex;

    public MarkIssueCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        requireNonNull(model);
        List<Issue> lastShownList = model.getFilteredIssueList();

        for (Issue i : lastShownList) {
            if (i.getIssueId().getIdInt() == targetIndex.getOneBased()) {
                Issue issueToMark= i;
                if (i.getStatus().equals(new Status(false))) {
                    i.getStatus().setStatus(true);
                } else {
                    i.getStatus().setStatus(false);
                }
                ui.showIssues();
                model.updateFilteredIssueList(PREDICATE_SHOW_ALL_ISSUES);
                return new CommandResult(String.format(MESSAGE_SUCCESS, issueToMark));
            }
        }

        ui.showIssues();
        model.updateFilteredIssueList(PREDICATE_SHOW_ALL_ISSUES);

        throw new CommandException(Messages.MESSAGE_ISSUE_NOT_FOUND);
    }
}
