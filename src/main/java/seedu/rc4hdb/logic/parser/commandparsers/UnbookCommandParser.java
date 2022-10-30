package seedu.rc4hdb.logic.parser.commandparsers;

import static seedu.rc4hdb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_TIME_PERIOD;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_VENUE_NAME;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import seedu.rc4hdb.logic.commands.venuecommands.UnbookCommand;
import seedu.rc4hdb.logic.parser.ArgumentMultimap;
import seedu.rc4hdb.logic.parser.ArgumentTokenizer;
import seedu.rc4hdb.logic.parser.ParserUtil;
import seedu.rc4hdb.logic.parser.Prefix;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;
import seedu.rc4hdb.model.venues.booking.BookingDescriptor;

/**
 * Parses input arguments and creates a new UnbookCommand object
 */
public class UnbookCommandParser implements CommandParser<UnbookCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the BookCommand
     * and returns a UnbookCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnbookCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DAY, PREFIX_TIME_PERIOD, PREFIX_VENUE_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_DAY, PREFIX_TIME_PERIOD, PREFIX_VENUE_NAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnbookCommand.MESSAGE_USAGE));
        }

        try {
            return new UnbookCommand(buildBookingDescriptor(argMultimap));
        } catch (NoSuchElementException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnbookCommand.MESSAGE_USAGE), e);
        }


    }

    /**
     * Builds a {@code BookingDescriptor} according to the arguments passed to {@code argMultimap}.
     */
    private static BookingDescriptor buildBookingDescriptor(ArgumentMultimap argMultimap) throws ParseException {
        BookingDescriptor bookingDescriptor = new BookingDescriptor();

        bookingDescriptor.setVenueName(ParserUtil.parseVenueName(argMultimap.getValue(PREFIX_VENUE_NAME).get()));
        bookingDescriptor.setHourPeriod(ParserUtil.parseHourPeriod(argMultimap.getValue(PREFIX_TIME_PERIOD).get()));
        bookingDescriptor.setDayOfWeek(ParserUtil.parseDay(argMultimap.getValue(PREFIX_DAY).get()));
        return bookingDescriptor;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
