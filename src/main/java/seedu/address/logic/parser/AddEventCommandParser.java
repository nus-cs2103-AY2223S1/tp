package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PURPOSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventTitle;
import seedu.address.model.event.Purpose;
import seedu.address.model.event.StartTime;

/**
 * Parses input arguments and creates a new AddEventCommand object
 */
public class AddEventCommandParser implements Parser<AddEventCommand> {

    /**
     * Parses the given String args for an AddEventCommand (i.e title of event;
     * date; time and purpose) and returns new AddEventCommand object for execution.
     * @param args String containing all user inputs excluding the command word
     * @return AddEventCommand object
     * @throws ParseException if user input is not in expected format
     */
    public AddEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT_TITLE, PREFIX_START_DATE,
                        PREFIX_START_TIME, PREFIX_PURPOSE);

        if (!arePrefixesPresent(argMultimap, PREFIX_EVENT_TITLE, PREFIX_START_DATE, PREFIX_START_TIME, PREFIX_PURPOSE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        }

        EventTitle eventTitle = ParserUtil.parseEventTitle(argMultimap.getValue(PREFIX_EVENT_TITLE).get());
        Date startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_START_DATE).get(), Boolean.TRUE);
        StartTime startTime = ParserUtil.parseStartTime(argMultimap.getValue(PREFIX_START_TIME).get());
        Purpose purpose = ParserUtil.parsePurpose(argMultimap.getValue(PREFIX_PURPOSE).get());

        Event event = new Event(eventTitle, startDate, startTime, purpose);

        return new AddEventCommand(event);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
