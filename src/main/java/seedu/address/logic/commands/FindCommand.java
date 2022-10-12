package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.application.CompanyContainsKeywordsPredicate;
import seedu.address.model.application.PositionContainsKeywordsPredicate;

/**
 * Finds and lists all applications in application book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all applications whose company "
            + "and positions contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " google grab byteDance software engineering";

    private final CompanyContainsKeywordsPredicate companyPredicate;
    private final PositionContainsKeywordsPredicate positionPredicate;

    /**
     * Constructs FindCommand object.
     * @param companyPredicate predicate that filters application with company according to keywords.
     * @param positionPredicate predicate that filters application with position according to keywords.
     */
    public FindCommand(CompanyContainsKeywordsPredicate companyPredicate,
                       PositionContainsKeywordsPredicate positionPredicate) {
        this.companyPredicate = companyPredicate;
        this.positionPredicate = positionPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredApplicationList(companyPredicate.or(positionPredicate));
        return new CommandResult(
                String.format(Messages.MESSAGE_APPLICATION_LISTED_OVERVIEW, model.getFilteredApplicationList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && companyPredicate.equals(((FindCommand) other).companyPredicate)
                && positionPredicate.equals(((FindCommand) other).positionPredicate)); // state check
    }
}

