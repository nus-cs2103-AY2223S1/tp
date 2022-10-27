package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TIME;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.MeetingTime;

/**
 * Parses input arguments and creates a new {@code AddMeetingCommand} object
 */
public class AddMeetingCommandParser implements Parser<AddMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddMeetingCommand}
     * and returns a {@code AddMeetingCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEETING_TIME);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddMeetingCommand.MESSAGE_USAGE), ive);
        }

        Set<MeetingTime> meetingTimeList = ParserUtil.parseMeetingTimes(argMultimap.getAllValues(PREFIX_MEETING_TIME));

        return new AddMeetingCommand(index, meetingTimeList);
    }
}
