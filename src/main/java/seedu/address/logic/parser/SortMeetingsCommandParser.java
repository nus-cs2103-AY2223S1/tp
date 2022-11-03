package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.SortMeetingsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input argument and creates a new SortMeetingCommand object
 */
public class SortMeetingsCommandParser implements Parser<SortMeetingsCommand> {
    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput asc or desc depending user input
     * @return a new command
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public Command parse(String userInput) throws ParseException {
        String upperCaseInput = userInput.strip().toUpperCase();
        if (upperCaseInput.equals(SortMeetingsCommand.ASCENDING_ARGS)) {
            return new SortMeetingsCommand(true);
        } else if (upperCaseInput.equals(SortMeetingsCommand.DESCENDING_ARGS)) {
            return new SortMeetingsCommand(false);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortMeetingsCommand.MESSAGE_USAGE));
        }
    }
}
