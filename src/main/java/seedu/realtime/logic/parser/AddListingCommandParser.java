package seedu.realtime.logic.parser;

import static seedu.realtime.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_ASKING_PRICE;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_LISTING_ID;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.realtime.logic.commands.AddListingCommand;
import seedu.realtime.logic.parser.exceptions.ParseException;
import seedu.realtime.model.listing.ListingId;
import seedu.realtime.model.offer.Price;
import seedu.realtime.model.person.Address;
import seedu.realtime.model.person.Name;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddListingCommandParser implements Parser<AddListingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddListingCommand
     * and returns an AddListingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddListingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LISTING_ID, PREFIX_ADDRESS, PREFIX_NAME, PREFIX_ASKING_PRICE);

        if (!arePrefixesPresent(argMultimap, PREFIX_LISTING_ID, PREFIX_ADDRESS, PREFIX_NAME, PREFIX_ASKING_PRICE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddListingCommand.MESSAGE_USAGE));
        }

        ListingId id = ParserUtil.parseListingId(argMultimap.getValue(PREFIX_LISTING_ID).get());
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Price askingPrice = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_ASKING_PRICE).get());

        return new AddListingCommand(id, address, name, askingPrice);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
