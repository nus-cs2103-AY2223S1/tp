package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_END_TIME_BEFORE_START_TIME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

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
                ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_DATE,
                        PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_DESCRIPTION);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_INDEX, PREFIX_DATE,
                PREFIX_START_TIME, PREFIX_DESCRIPTION, PREFIX_END_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
        }

        Index clientIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
        MeetingDate date = new MeetingDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get(), "meeting"));
        MeetingTime startTime = ParserUtil.parseTime(argMultimap.getValue(PREFIX_START_TIME).get());
        MeetingTime endTime = ParserUtil.parseTime(argMultimap.getValue(PREFIX_END_TIME).get());
        if (endTime.isBefore(startTime)) {
            throw new ParseException(MESSAGE_END_TIME_BEFORE_START_TIME);
        }
        Description description = new Description(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        return new AddMeetingCommand(clientIndex, date, startTime, endTime, description);
    }
}
