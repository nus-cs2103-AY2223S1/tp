package seedu.watson.logic.parser;

import static seedu.watson.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.watson.logic.commands.SortCommand;
import seedu.watson.logic.parser.exceptions.ParseException;

/**
 * Parses input argument and creates a new SortCommand object
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
