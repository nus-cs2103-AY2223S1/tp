package seedu.realtime.logic.parser;

import static seedu.realtime.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_LISTING_ID;

import java.util.stream.Stream;

import seedu.realtime.logic.commands.ViewListingClientsCommand;
import seedu.realtime.logic.parser.exceptions.ParseException;
import seedu.realtime.model.listing.ListingId;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class ViewListingClientsCommandParser implements Parser<ViewListingClientsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewListingClientsCommand
     * and returns an ViewListingClientsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewListingClientsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LISTING_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_LISTING_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewListingClientsCommand.MESSAGE_USAGE));
        }

        ListingId id = ParserUtil.parseListingId(argMultimap.getValue(PREFIX_LISTING_ID).get());

        return new ViewListingClientsCommand(id);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}


