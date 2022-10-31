package seedu.address.logic.parser.tasks;

import seedu.address.logic.commands.tasks.ForEachTaskCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser to parse user input for ForEachTask Command
 */
public class ForEachTaskCommandParser implements Parser<ForEachTaskCommand> {

    @Override
    public ForEachTaskCommand parse(String userInput) throws ParseException {
        return new ForEachTaskCommand(userInput.trim());
    }

}
