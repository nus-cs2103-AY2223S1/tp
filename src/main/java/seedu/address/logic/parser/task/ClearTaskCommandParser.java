package seedu.address.logic.parser.task;

import seedu.address.logic.commands.task.ClearTaskCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkTaskCommand object
 */
public class ClearTaskCommandParser implements Parser<ClearTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkTaskCommand
     * and returns an MarkTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearTaskCommand parse(String args) throws ParseException {
        return new ClearTaskCommand();
    }
}


