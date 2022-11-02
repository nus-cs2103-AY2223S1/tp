package seedu.address.logic.commands.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_ISSUE_ID;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_PROJECT_ID;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_PROJECT_NAME;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_URGENCY;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.parser.predicates.IssueContainsKeywordsPredicate;
import seedu.address.model.Model;
import seedu.address.ui.Ui;

/**
 * Represents a class to find and filter issue list.
 */
public class FindIssueCommand extends IssueCommand {

    public static final String COMMAND_FLAG = "-f";
    public static final String MESSAGE_SUCCESS = "Filtered list of issues shown.";
    private static final String MESSAGE_ISSUE_NOT_FOUND = "An issue matching requirements not found.";

    public static final String MESSAGE_FIND_ISSUE_USAGE = COMMAND_WORD + " " + COMMAND_FLAG
            + ": Finds issues by keyword.\n"
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_STATUS + "STATUS "
            + PREFIX_URGENCY + "URGENCY "
            + PREFIX_PROJECT_NAME + "PROJECT NAME "
            + PREFIX_PROJECT_ID + "PROJECT ID "
            + PREFIX_ISSUE_ID + "ISSUE ID \n"
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_TITLE + "This is an issue "
            + PREFIX_STATUS + "COMPLETED "
            + PREFIX_URGENCY + "HIGH "
            + PREFIX_PROJECT_NAME + "DevEnable "
            + PREFIX_PROJECT_ID + "1 "
            + PREFIX_ISSUE_ID + "3 ";

    private final IssueContainsKeywordsPredicate predicate;

    public FindIssueCommand(IssueContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) {
        requireNonNull(model);
        ui.showIssues();
        model.updateFilteredIssueList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ISSUES_LISTED_OVERVIEW, model.getFilteredIssueList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindIssueCommand // instanceof handles nulls
                && predicate.equals(((FindIssueCommand) other).predicate)); // state check
    }
}
