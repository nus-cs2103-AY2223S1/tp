package seedu.realtime.logic.parser;

import static seedu.realtime.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_LISTING_ID;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.realtime.logic.commands.AddTagsToListingCommand;
import seedu.realtime.logic.parser.exceptions.ParseException;
import seedu.realtime.model.listing.ListingId;
import seedu.realtime.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddTagsToListingCommand object.
 */
public class AddTagsToListingCommandParser implements Parser<AddTagsToListingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTagsToListingCommand
     * and returns an AddTagsToListingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTagsToListingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG, PREFIX_LISTING_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_TAG, PREFIX_LISTING_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTagsToListingCommand.MESSAGE_USAGE));
        }

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        ListingId listingId = ParserUtil.parseListingId(argMultimap.getValue(PREFIX_LISTING_ID).get());

        return new AddTagsToListingCommand(tagList, listingId);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
