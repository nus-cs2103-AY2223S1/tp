package seedu.boba.logic.parser;

import seedu.boba.logic.commands.CalculateCommand;
import seedu.boba.logic.parser.exceptions.ParseException;

/**
 * Parser for calculate command that create a calculator.
 */
public class CalculateCommandParser implements Parser<CalculateCommand> {

    @Override
    public CalculateCommand parse(String userInput) throws ParseException {
        return new CalculateCommand(userInput);
    }
}
