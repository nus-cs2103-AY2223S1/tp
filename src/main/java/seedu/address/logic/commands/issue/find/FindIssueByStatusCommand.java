package seedu.address.logic.commands.issue.find;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.issue.IssueCommand;
import seedu.address.model.Model;
import seedu.address.model.issue.predicates.PriorityMatchesKeywordsPredicate;
import seedu.address.model.issue.predicates.StatusMatchesKeywordsPredicate;
import seedu.address.ui.Ui;

import static java.util.Objects.requireNonNull;

public class FindIssueByStatusCommand extends FindIssueCommand {
    private final StatusMatchesKeywordsPredicate predicate;

    public FindIssueByStatusCommand(StatusMatchesKeywordsPredicate predicate) {
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
                || (other instanceof FindIssueByStatusCommand // instanceof handles nulls
                && predicate.equals(((FindIssueByStatusCommand) other).predicate)); // state check
    }
}
