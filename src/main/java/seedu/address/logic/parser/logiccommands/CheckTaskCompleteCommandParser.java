package seedu.address.logic.parser.logiccommands;

import seedu.address.logic.commands.logicalcommand.CheckTaskCompleteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser to parse user input to check is task complete comand
 */
public class CheckTaskCompleteCommandParser implements Parser<CheckTaskCompleteCommand> {

    @Override
    public CheckTaskCompleteCommand parse(String userInput) throws ParseException {
        return new CheckTaskCompleteCommand();
    }
}
