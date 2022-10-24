package seedu.address.logic.commands.issue.find;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.logic.parser.predicates.DescriptionContainsKeywordsPredicate;
import seedu.address.ui.Ui;

/**
 * Represents a class to find and filter issue list by description.
 */
public class FindIssueByDescriptionCommand extends FindIssueCommand {
    private final DescriptionContainsKeywordsPredicate predicate;

    public FindIssueByDescriptionCommand(DescriptionContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) {
        requireNonNull(model);
        model.updateFilteredIssueList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ISSUES_LISTED_OVERVIEW, model.getFilteredIssueList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindIssueByDescriptionCommand // instanceof handles nulls
                && predicate.equals(((FindIssueByDescriptionCommand) other).predicate)); // state check
    }
}
