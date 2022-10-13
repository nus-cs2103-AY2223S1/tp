package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingTime;

/**
 * Parses input arguments and creates a new AddMeetingCommand object.
 */
public class AddMeetingCommandParser implements Parser<AddMeetingCommand> {

    @Override
    public AddMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_DATE, PREFIX_TIME, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_INDEX, PREFIX_DATE, PREFIX_TIME, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
        }

        Index clientIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
        MeetingDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        MeetingTime time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
        Description description = new Description(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        return new AddMeetingCommand(clientIndex, date, time, description);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
