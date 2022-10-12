package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.CreateMeetingCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CreateMeetingCommand object
 */
public class CreateMeetingCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CreateMeetingCommand
     * and returns a CreateMeetingCommand object for execution.
     * @return a command to create a new meeting
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateMeetingCommand parse(String args) throws ParseException {
        String meetingInfo = args.trim();
        if (meetingInfo.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateMeetingCommand.MESSAGE_USAGE));
        }

        // String[] nameKeywords = trimmedArgs.split("\\s+"); // split by whitespace

        return new CreateMeetingCommand(meetingInfo);
    }

}
