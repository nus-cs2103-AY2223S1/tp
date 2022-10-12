package seedu.phu.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.phu.commons.core.Messages;
import seedu.phu.model.Model;
import seedu.phu.model.internship.ContainsKeywordsPredicate;

/**
 * Finds and lists all internships in internship book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all internships whose specified category"
            + " contain any of the specified keywords (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: find [c/CATEGORY] KEYWORDS...\n"
            + "Example: " + COMMAND_WORD + " c/p engineer writer";

    private final ContainsKeywordsPredicate predicate;

    public FindCommand(ContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, model.getFilteredInternshipList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
