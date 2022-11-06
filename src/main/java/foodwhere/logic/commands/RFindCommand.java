package foodwhere.logic.commands;

import static java.util.Objects.requireNonNull;

import foodwhere.commons.core.Messages;
import foodwhere.logic.parser.CliSyntax;
import foodwhere.model.Model;
import foodwhere.model.review.ReviewContainsKeywordsPredicate;

/**
 * Finds and lists all reviews in FoodWhere whose names or tags contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class RFindCommand extends Command {

    public static final String COMMAND_WORD = "rfind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all reviews whose names or tags contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + CliSyntax.PREFIX_NAME + "[NAME_KEYWORDS]... "
            + CliSyntax.PREFIX_TAG + "[TAG_KEYWORDS]... "
            + "Example: "
            + CliSyntax.PREFIX_NAME + "Chicken "
            + CliSyntax.PREFIX_TAG + "opensDaily ";

    private final ReviewContainsKeywordsPredicate predicate;

    /**
     * Creates an RFindCommand to find the specified {@code Review}s.
     *
     * @param predicate Predicate to filter the review list.
     */
    public RFindCommand(ReviewContainsKeywordsPredicate predicate) {
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
