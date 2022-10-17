package foodwhere.logic.commands;

import static java.util.Objects.requireNonNull;

import foodwhere.commons.core.Messages;
import foodwhere.model.Model;
import foodwhere.model.review.NameContainsStallPredicate;

/**
 * Finds and lists all stalls in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class RFindCommand extends Command {

    public static final String COMMAND_WORD = "rfind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all reviews whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " John Doe Eatery";

    private final NameContainsStallPredicate predicate;

    public RFindCommand(NameContainsStallPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredReviewList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_REVIEWS_LISTED_OVERVIEW, model.getFilteredReviewList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RFindCommand // instanceof handles nulls
                && predicate.equals(((RFindCommand) other).predicate)); // state check
    }
}
