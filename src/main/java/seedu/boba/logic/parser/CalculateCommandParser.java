package seedu.boba.logic.parser;

import static seedu.boba.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.boba.logic.commands.CalculateCommand;
import seedu.boba.logic.parser.exceptions.ParseException;


/**
 * CommandParser for calculate command that create a calculator.
 */
public class CalculateCommandParser implements CommandParser<CalculateCommand> {

    @Override
    public CalculateCommand parse(String userInput) throws ParseException {
        if (userInput.trim().equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CalculateCommand.MESSAGE_USAGE));
        }
        return new CalculateCommand(userInput);
    }
}
