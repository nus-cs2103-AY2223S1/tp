package seedu.address.logic.commands.issue;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_ISSUE_NOT_FOUND;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ISSUES;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueId;
import seedu.address.model.issue.Status;
import seedu.address.ui.Ui;

/**
 * Command to mark an issue as complete
 */
public class MarkIssueCommand extends IssueCommand {

    public static final String COMMAND_FLAG = "-m";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMMAND_FLAG
            + ": Marks the issue identified by the issue id as complete \n"
            + "Parameters: ISSUE_ID (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " 1";


    public static final String MESSAGE_SUCCESS = "Issue marked completed: %1$s";

    private final Status newStatus;
    private final IssueId issueId;

    /**
     * Creates a MarkIssueCommand to mark the specified {@code Issue} as complete
     */
    public MarkIssueCommand(Status newStatus, IssueId issueId) {
        requireAllNonNull(newStatus, issueId);
        this.newStatus = newStatus;
        this.issueId = issueId;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        requireNonNull(model);
        ui.showIssues();
        if (!model.hasIssueId(this.issueId.getIdInt())) {
            throw new CommandException(MESSAGE_ISSUE_NOT_FOUND);
        }
        Issue toMarkIssue = model.getIssueById(issueId.getIdInt());
        toMarkIssue.setStatus(newStatus);
        model.updateFilteredIssueList(PREDICATE_SHOW_ALL_ISSUES);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toMarkIssue));
    }
}
