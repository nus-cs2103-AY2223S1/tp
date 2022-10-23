package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LISTING_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.listing.ListingID;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and create a new AddMeetingCommand object.
 */
public class AddMeetingCommandParser implements Parser<AddMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of AddMeetingCommand
     * and returns an AddMeetingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(args, PREFIX_LISTING_ID, PREFIX_NAME, PREFIX_DATETIME);

        if (!arePrefixesPresent(argMultiMap, PREFIX_LISTING_ID, PREFIX_NAME, PREFIX_DATETIME)
                || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
        }

        ListingID listing = ParserUtil.parseListingID(argMultiMap.getValue(PREFIX_LISTING_ID).get());
        Name name = ParserUtil.parseName(argMultiMap.getValue(PREFIX_NAME).get());
        LocalDateTime dateTime = ParserUtil.parseDateTime(argMultiMap.getValue(PREFIX_DATETIME).get());

        Meeting meeting = new Meeting(name, listing, dateTime);
        return new AddMeetingCommand(meeting);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
