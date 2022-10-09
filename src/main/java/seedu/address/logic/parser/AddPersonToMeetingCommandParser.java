package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddPersonToMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CreateMeetingCommand object
 */
public class AddPersonToMeetingCommandParser implements Parser<AddPersonToMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CreateMeetingCommand
     * and returns a CreateMeetingCommand object for execution.
     * @return a command to create a new meeting
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPersonToMeetingCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonToMeetingCommand.MESSAGE_USAGE));
        }

        String meetingInfo = trimmedArgs;

        return new AddPersonToMeetingCommand(meetingInfo);
    }

}
