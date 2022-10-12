package paymelah.logic.commands;

import static java.util.Objects.requireNonNull;

import paymelah.commons.core.Messages;
import paymelah.model.Model;
import paymelah.model.person.DebtContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book associated with a debt that
 * contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindDebtCommand extends Command {
    public static final String COMMAND_WORD = "finddebt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons associated with a debt that "
            + "contains the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " burger meal";

    private final DebtContainsKeywordsPredicate predicate;

    public FindDebtCommand(DebtContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindDebtCommand // instanceof handles nulls
                        && predicate.equals(((FindDebtCommand) other).predicate)); // state check
    }
}
