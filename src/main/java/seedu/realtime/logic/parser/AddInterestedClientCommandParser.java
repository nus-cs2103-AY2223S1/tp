package seedu.realtime.logic.parser;

import static seedu.realtime.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_LISTING_ID;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.realtime.logic.commands.AddClientCommand;
import seedu.realtime.logic.commands.AddInterestedClientCommand;
import seedu.realtime.logic.parser.exceptions.ParseException;
import seedu.realtime.model.listing.ListingId;
import seedu.realtime.model.person.Name;


/**
 * Parses input arguments and creates a new AddInterestedClientCommand object
 */
public class AddInterestedClientCommandParser implements Parser<AddInterestedClientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddInterestedClientCommand
     * and returns an AddInterestedClientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddInterestedClientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_LISTING_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_LISTING_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClientCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        ListingId listingId = ParserUtil.parseListingId(argMultimap.getValue(PREFIX_LISTING_ID).get());

        return new AddInterestedClientCommand(name, listingId);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
