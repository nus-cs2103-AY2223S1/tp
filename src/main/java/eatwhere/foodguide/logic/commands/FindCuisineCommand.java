package eatwhere.foodguide.logic.commands;

import static java.util.Objects.requireNonNull;

import eatwhere.foodguide.commons.core.Messages;
import eatwhere.foodguide.model.Model;
import eatwhere.foodguide.model.eatery.CuisineContainsKeywordsPredicate;

/**
 * Finds and lists all eateries in food guide whose tags contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCuisineCommand extends Command {

    public static final String COMMAND_WORD = "findCuisine";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all eateries whose cuisine matches any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " mala";

    private final CuisineContainsKeywordsPredicate predicate;

    public FindCuisineCommand(CuisineContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEateryList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EATERIES_LISTED_OVERVIEW, model.getFilteredEateryList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCuisineCommand // instanceof handles nulls
                && predicate.equals(((FindCuisineCommand) other).predicate)); // state check
    }
}
