package seedu.workbook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.workbook.commons.core.Messages;
import seedu.workbook.model.Model;
import seedu.workbook.model.internship.CompanyContainsKeywordsPredicate;

/**
 * Finds and lists all internships in WorkBook whose company name contains
 * any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    /** Command word to execute the find command */
    public static final String COMMAND_WORD = "find";

    /** Help message to execute the find command */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all internships whose company name contains any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    /** Keyword to search for */
    private final CompanyContainsKeywordsPredicate predicate;

    public FindCommand(CompanyContainsKeywordsPredicate predicate) {
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
