package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeletePersonFromMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CreateMeetingCommand object
 */
public class DeletePersonFromMeetingCommandParser implements Parser<DeletePersonFromMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CreateMeetingCommand
     * and returns a CreateMeetingCommand object for execution.
     * @return a command to create a new meeting
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeletePersonFromMeetingCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonFromMeetingCommand.MESSAGE_USAGE));
        }

        String meetingInfo = trimmedArgs;

        return new DeletePersonFromMeetingCommand(meetingInfo);
    }

}
