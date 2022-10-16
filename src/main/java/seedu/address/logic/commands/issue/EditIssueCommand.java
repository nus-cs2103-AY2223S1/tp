package seedu.address.logic.commands.issue;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.issue.Issue;
import seedu.address.ui.Ui;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_ISSUE_ID;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_PRIORITY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ISSUES;

/**
 * Edit issue command to edit issues
 */
public class EditIssueCommand extends IssueCommand {

    public static final String COMMAND_FLAG = "-e";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an issue in the address book. "
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

    private final Issue toEditIssue;

    /**
     * Creates an EditIssueCommand to edit the specified {@code Issue}
     */
    public EditIssueCommand(Issue issue) {
        requireNonNull(issue);
        toEditIssue = issue;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        ui.showIssues();
        model.updateFilteredIssueList(PREDICATE_SHOW_ALL_ISSUES);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toEditIssue));
    }
}

