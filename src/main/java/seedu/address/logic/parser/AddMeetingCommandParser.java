package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingTime;

/**
 * Parses input arguments and creates a new AddMeetingCommand object.
 */
public class AddMeetingCommandParser implements Parser<AddMeetingCommand> {

    @Override
    public AddMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE, PREFIX_TIME);

        String clientName = argMultimap.getValue(PREFIX_NAME).get();
        MeetingDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        MeetingTime time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());;

        return new AddMeetingCommand(clientName, date, time);
    }

}
