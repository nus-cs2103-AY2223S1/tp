package seedu.address.logic.parser.tasks;

import seedu.address.logic.commands.tasks.TaskDeleteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new TaskDeleteCommand object
 */
public class TaskDeleteCommandParser implements Parser<TaskDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TaskDeleteCommand
     * and returns a TaskDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public TaskDeleteCommand parse(String args) throws ParseException {
        return new TaskDeleteCommand();
    }
}
