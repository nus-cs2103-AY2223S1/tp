package seedu.waddle.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.waddle.commons.core.Messages;
import seedu.waddle.model.Model;
import seedu.waddle.model.itinerary.NameContainsKeywordsPredicate;

/**
 * Finds and lists all itineraries in Waddle whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all itineraries whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " summer";

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredItineraryList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ITINERARIES_LISTED_OVERVIEW, model.getFilteredItineraryList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
