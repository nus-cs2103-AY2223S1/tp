package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.project.ProjectNameContainsKeywordsPredicate;

/**
 * Finds and lists all projects in HR Pro Max++ whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "findproj";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all projects whose names contain any of "
            + "the exact specified keywords (but can be case-insensitive) and displays them as a "
            + "list with index numbers.\n"
            + "A Project called CS2103 TP can be found using these keywords: tp, TP, cs2103\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS2103T";

    private final ProjectNameContainsKeywordsPredicate predicate;

    public FindCommand(ProjectNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredProjectList(predicate);
        return (model.getFilteredProjectList().size() == 1)
                ? new CommandResult(String.format(Messages.MESSAGE_PROJECTS_LISTED_SINGULAR_OVERVIEW,
                        model.getFilteredProjectList().size()))
                : new CommandResult(String.format(Messages.MESSAGE_PROJECTS_LISTED_PLURAL_OVERVIEW,
                        model.getFilteredProjectList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
