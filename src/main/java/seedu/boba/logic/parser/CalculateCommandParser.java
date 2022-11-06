package seedu.boba.logic.parser;

import seedu.boba.logic.commands.CalculateCommand;
import seedu.boba.logic.parser.exceptions.ParseException;

/**
 * CommandParser for calculate command that create a calculator.
 */
public class CalculateCommandParser implements CommandParser<CalculateCommand> {

    @Override
    public CalculateCommand parse(String userInput) throws ParseException {
        return new CalculateCommand(userInput);
    }
}
