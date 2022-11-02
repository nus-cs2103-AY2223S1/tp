package seedu.address.logic.commands.issue;

import static seedu.address.commons.core.Messages.MESSAGE_ISSUE_NOT_FOUND;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_ISSUE_ID;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_URGENCY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ISSUES;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Deadline;
import seedu.address.model.Model;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueId;
import seedu.address.model.issue.Title;
import seedu.address.model.issue.Urgency;
import seedu.address.ui.Ui;

/**
 * Edit issue command to edit issues
 */
public class EditIssueCommand extends IssueCommand {

    public static final String COMMAND_FLAG = "-e";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMMAND_FLAG
            + ": Edits an issue in the project book. \n"
            + "Parameters: "
            + PREFIX_ISSUE_ID + "ISSUE_ID "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_URGENCY + "URGENCY(0, 1, 2, 3) "
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_ISSUE_ID + "1 "
            + PREFIX_TITLE + "To Edit Class "
            + PREFIX_DEADLINE + "2022-03-05 "
            + PREFIX_URGENCY + "1 ";

    public static final String MESSAGE_SUCCESS = "Issue %1$s has been edited";
    private final Title newTitle;
    private final Urgency newUrgency;
    private final Deadline newDeadline;
    private final IssueId issueId;


    /**
     * Creates an EditIssueCommand to edit the specified {@code Issue}
     */
    public EditIssueCommand(Title newTitle, Deadline newDeadline, Urgency newUrgency, IssueId issueId) {
        // NULL values passed into constructor here represent absent optional inputs
        this.newTitle = newTitle;
        this.newDeadline = newDeadline;
        this.newUrgency = newUrgency;
        this.issueId = issueId;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        ui.showIssues();
        if (!model.hasIssueId(this.issueId.getIdInt())) {
            throw new CommandException(MESSAGE_ISSUE_NOT_FOUND);
        }
        Issue toEditIssue = model.getIssueById(issueId.getIdInt());

        if (newTitle != null) {
            toEditIssue.setTitle(newTitle);
        }

        if (newDeadline != null) {
            toEditIssue.setDeadline(newDeadline);
        }

        if (newUrgency != null) {
            toEditIssue.setUrgency(newUrgency);
        }

        model.updateFilteredIssueList(PREDICATE_SHOW_ALL_ISSUES);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toEditIssue));
    }
}

