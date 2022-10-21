package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASKING_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;


import java.util.stream.Stream;

import seedu.address.logic.commands.AddListingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.listing.Listing;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddListingCommandParser implements Parser<AddListingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddListingCommand
     * and returns an AddListingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddListingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_ADDRESS, PREFIX_NAME, PREFIX_ASKING_PRICE);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_ADDRESS, PREFIX_NAME, PREFIX_ASKING_PRICE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddListingCommand.MESSAGE_USAGE));
        }

        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Integer askingPrice = ParserUtil.parseAskingPrice(Integer.parseInt(argMultimap
                .getValue(PREFIX_ASKING_PRICE).get()));

        Listing listing = new Listing(address, name, askingPrice);

        return new AddListingCommand(listing);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
