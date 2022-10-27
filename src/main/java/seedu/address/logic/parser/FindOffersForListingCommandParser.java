package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.logic.commands.FindOffersForListingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.offer.OfferContainsListingIdPredicate;

/**
 * Parses input arguments and creates a new FindOffersForListingCommand object
 */
public class FindOffersForListingCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the FindOffersForListingCommand
     * and returns a FindOffersForListingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindOffersForListingCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOffersForListingCommand.MESSAGE_USAGE));
        }

        String[] listingId = trimmedArgs.split("\\s+");

        return new FindOffersForListingCommand(new OfferContainsListingIdPredicate(List.of(listingId)));
    }
}
