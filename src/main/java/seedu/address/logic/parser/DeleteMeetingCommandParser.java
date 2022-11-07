package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TIME;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.DeleteMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.MeetingTime;

/**
 * Parses input arguments and creates a new {@code DeleteMeetingCommand} object
 */
public class DeleteMeetingCommandParser implements Parser<DeleteMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code DeleteMeetingCommand}
     * and returns a {@code DeleteMeetingCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEETING_TIME);

        if (!argMultimap.getValue(PREFIX_MEETING_TIME).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMeetingCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteMeetingCommand.MESSAGE_USAGE), ive);
        }

        MeetingTime meetingTime =
                ParserUtil.parseMeetingTime(argMultimap.getValue(PREFIX_MEETING_TIME).get());

        return new DeleteMeetingCommand(index, meetingTime);
    }
}

