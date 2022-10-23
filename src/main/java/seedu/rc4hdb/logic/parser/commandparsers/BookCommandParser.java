package seedu.rc4hdb.logic.parser.commandparsers;

import static seedu.rc4hdb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_VENUE;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.stream.Stream;

import seedu.rc4hdb.commons.core.index.Index;
import seedu.rc4hdb.logic.commands.venuecommands.BookCommand;
import seedu.rc4hdb.logic.parser.ArgumentMultimap;
import seedu.rc4hdb.logic.parser.ArgumentTokenizer;
import seedu.rc4hdb.logic.parser.Parser;
import seedu.rc4hdb.logic.parser.ParserUtil;
import seedu.rc4hdb.logic.parser.Prefix;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;
import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.model.venues.booking.BookingDescriptor;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.Time;

/**
 * Parses input arguments and creates a new BookCommand object
 */
public class BookCommandParser implements Parser<BookCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the BookCommand
     * and returns an BookCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BookCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DAY, PREFIX_END_TIME, PREFIX_START_TIME,
                        PREFIX_VENUE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            System.out.println("index error");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BookCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_DAY, PREFIX_END_TIME, PREFIX_START_TIME,
                PREFIX_VENUE)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BookCommand.MESSAGE_USAGE));
        }

        BookingDescriptor bookingDescriptor = new BookingDescriptor();

        Venue venue = ParserUtil.parseVenue(argMultimap.getValue(PREFIX_VENUE).get());
        Time startTime = ParserUtil.parseTime(argMultimap.getValue(PREFIX_START_TIME).get());
        LocalTime startLocalTime = startTime.time;
        Time endTime = ParserUtil.parseTime(argMultimap.getValue(PREFIX_END_TIME).get());
        LocalTime endLocalTime = endTime.time;
        Day day = ParserUtil.parseDay(argMultimap.getValue(PREFIX_DAY).get());
        DayOfWeek dayOfWeek = day.dayOfWeek;

        bookingDescriptor.setVenue(venue);
        bookingDescriptor.setStartTime(startLocalTime);
        bookingDescriptor.setEndTime(endLocalTime);
        bookingDescriptor.setDayOfWeek(dayOfWeek);

        return new BookCommand(index, bookingDescriptor);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
