package seedu.address.logic.commands.project.find;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.project.predicates.RepositoryContainsKeywordsPredicate;
import seedu.address.ui.Ui;

/**
 * Represents a class to find and filter project list by repository.
 */
public class FindProjectByRepositoryCommand extends FindProjectCommand {
    private final RepositoryContainsKeywordsPredicate predicate;

    public FindProjectByRepositoryCommand(RepositoryContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) {
        requireNonNull(model);
        model.updateFilteredProjectList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PROJECTS_LISTED_OVERVIEW, model.getFilteredProjectList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindProjectByRepositoryCommand // instanceof handles nulls
                && predicate.equals(((FindProjectByRepositoryCommand) other).predicate)); // state check
    }
}
