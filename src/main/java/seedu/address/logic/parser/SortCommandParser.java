package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input argument and creates a new SortMeetingCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {
    @Override
    public SortCommand parse(String userInput) throws ParseException {
        String upperCaseInput = userInput.trim().toUpperCase();
        if (upperCaseInput.equals(SortCommand.ASCENDING_ARGS)) {
            return new SortCommand(true);
        } else if (upperCaseInput.equals(SortCommand.DESCENDING_ARGS)) {
            return new SortCommand(false);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }
}
