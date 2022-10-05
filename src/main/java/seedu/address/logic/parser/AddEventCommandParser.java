package seedu.address.logic.parser;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PURPOSE;

import seedu.address.model.event.Event;

public class AddEventCommandParser implements Parser<AddEventCommand>{

    public AddEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT_TITLE, PREFIX_DATE, PREFIX_TIME, PREFIX_PURPOSE);

        if (!arePrefixesPresent(argMultimap, PREFIX_EVENT_TITLE, PREFIX_DATE, PREFIX_TIME, PREFIX_PURPOSE)
        || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        }

        String eventTitle = ParserUtil.parseEventTitle(argMultimap.getValue(PREFIX_EVENT_TITLE).get());
        String date = ParserUtil.parseEventTitle(argMultimap.getValue(PREFIX_DATE).get());
        String time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
        String purpose = ParserUtil.parsePurpose(argMultimap.getValue(PREFIX_PURPOSE).get());

        Event event = new Event(eventTitle, date, time, purpose);

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
