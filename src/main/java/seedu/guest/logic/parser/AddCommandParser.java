package seedu.guest.logic.parser;

import static seedu.guest.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_DATE_RANGE;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_NUMBER_OF_GUESTS;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_REQUEST;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_ROOM;
import static seedu.guest.model.guest.IsRoomClean.DEFAULT_IS_ROOM_CLEAN;

import java.util.stream.Stream;

import seedu.guest.logic.commands.AddCommand;
import seedu.guest.logic.parser.exceptions.ParseException;
import seedu.guest.model.guest.Bill;
import seedu.guest.model.guest.DateRange;
import seedu.guest.model.guest.Email;
import seedu.guest.model.guest.Guest;
import seedu.guest.model.guest.IsRoomClean;
import seedu.guest.model.guest.Name;
import seedu.guest.model.guest.NumberOfGuests;
import seedu.guest.model.guest.Phone;
import seedu.guest.model.guest.Request;
import seedu.guest.model.guest.Room;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ROOM, PREFIX_DATE_RANGE, PREFIX_NUMBER_OF_GUESTS, PREFIX_REQUEST);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ROOM,
                PREFIX_DATE_RANGE, PREFIX_NUMBER_OF_GUESTS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_REQUEST)) {
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
            Room room = ParserUtil.parseRoom(argMultimap.getValue(PREFIX_ROOM).get());
            DateRange dateRange = ParserUtil.parseDateRange(argMultimap.getValue(PREFIX_DATE_RANGE).get());
            NumberOfGuests numberOfGuests = ParserUtil
                    .parseNumberOfGuests(argMultimap.getValue(PREFIX_NUMBER_OF_GUESTS).get());
            IsRoomClean isRoomClean = new IsRoomClean(DEFAULT_IS_ROOM_CLEAN);
            Bill bill = new Bill();
            Request request = ParserUtil.parseRequest(argMultimap.getValue(PREFIX_REQUEST).get());
            Guest guest = new Guest(name, phone, email, room, dateRange, numberOfGuests, isRoomClean, bill, request);
            return new AddCommand(guest);
        } else {
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
            Room room = ParserUtil.parseRoom(argMultimap.getValue(PREFIX_ROOM).get());
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
            DateRange dateRange = ParserUtil.parseDateRange(argMultimap.getValue(PREFIX_DATE_RANGE).get());
            NumberOfGuests numberOfGuests = ParserUtil
                    .parseNumberOfGuests(argMultimap.getValue(PREFIX_NUMBER_OF_GUESTS).get());
            IsRoomClean isRoomClean = new IsRoomClean(DEFAULT_IS_ROOM_CLEAN);
            Bill bill = new Bill();
            Request request = new Request();
            Guest guest = new Guest(name, phone, email, room, dateRange, numberOfGuests, isRoomClean, bill, request);
            return new AddCommand(guest);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
