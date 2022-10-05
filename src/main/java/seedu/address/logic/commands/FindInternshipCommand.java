package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.internship.NameContainsKeywordsPredicate;

/**
 * Finds and lists all internships in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive, and only the company name is checked.
 */
public class FindInternshipCommand extends Command {

    public static final String COMMAND_WORD = "findi";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all internships which company names "
            + "contains any of the specified keywords (case-insensitive) and displays them as a list with "
            + "index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " abc pte ltd";

    private final NameContainsKeywordsPredicate predicate;

    public FindInternshipCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW,
                        model.getFilteredInternshipList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindInternshipCommand // instanceof handles nulls
                && predicate.equals(((FindInternshipCommand) other).predicate)); // state check
    }
}
