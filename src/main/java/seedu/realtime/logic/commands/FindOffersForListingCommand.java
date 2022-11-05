package seedu.realtime.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.realtime.commons.core.Messages;
import seedu.realtime.model.Model;
import seedu.realtime.model.offer.OfferContainsListingIdPredicate;

/**
 * Finds and lists all offers in address book for the specified Listing ID.
 * Keyword matching is case-insensitive.
 */
public class FindOffersForListingCommand extends Command {

    public static final String COMMAND_WORD = "findO";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all offers in address book for "
            + "the specified Listing ID (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: LISTING ID \n"
            + "Example: " + COMMAND_WORD + " 007";

    private final OfferContainsListingIdPredicate predicate;

    public FindOffersForListingCommand(OfferContainsListingIdPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOfferList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_OFFERS_LISTED_OVERVIEW, model.getFilteredOfferList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindOffersForListingCommand // instanceof handles nulls
                && predicate.equals(((FindOffersForListingCommand) other).predicate)); // state check
    }
}
