package seedu.address.logic.parser;

import seedu.address.logic.commands.CalculateCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser for calculate command that create a calculator.
 */
public class CalculateCommandParser implements Parser<CalculateCommand> {

    @Override
    public CalculateCommand parse(String userInput) throws ParseException {
        return new CalculateCommand(userInput);
    }
}
