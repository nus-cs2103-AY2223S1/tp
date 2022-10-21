package seedu.address.logic.commands.issue;

import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_ISSUE_ID;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_PRIORITY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ISSUES;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Deadline;
import seedu.address.model.Model;
import seedu.address.model.issue.Description;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueId;
import seedu.address.model.issue.Priority;
import seedu.address.ui.Ui;

/**
 * Edit issue command to edit issues
 */
public class EditIssueCommand extends IssueCommand {

    public static final String COMMAND_FLAG = "-e";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMMAND_FLAG
             + ": Edits an issue in the address book. "
            + "Parameters: "
            + PREFIX_ISSUE_ID + "ISSUE_ID "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_PRIORITY + "PRIORITY "
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_ISSUE_ID + "1 "
            + PREFIX_DESCRIPTION + "To Edit Class "
            + PREFIX_DEADLINE + "2022-03-05 "
            + PREFIX_PRIORITY + "1 ";

    public static final String MESSAGE_SUCCESS = "Issue %1$s has been edited";
    private final Description newDescription;
    private final Priority newPriority;
    private final Deadline newDeadline;
    private final IssueId issueId;


    /**
     * Creates an EditIssueCommand to edit the specified {@code Issue}
     */
    public EditIssueCommand(Description newDescription, Deadline newDeadline, Priority newPriority, IssueId issueId) {
        // NULL values passed into constructor here represent absent optional inputs
        this.newDescription = newDescription;
        this.newDeadline = newDeadline;
        this.newPriority = newPriority;
        this.issueId = issueId;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        ui.showIssues();
        Issue toEditIssue = model.getIssueById(issueId.getIdInt());

        if (newDescription != null) {
            toEditIssue.setDescription(newDescription);
        }

        if (newDeadline != null) {
            toEditIssue.setDeadline(newDeadline);
        }

        if (newPriority != null) {
            toEditIssue.setPriority(newPriority);
        }

        model.updateFilteredIssueList(PREDICATE_SHOW_ALL_ISSUES);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toEditIssue));
    }
}

