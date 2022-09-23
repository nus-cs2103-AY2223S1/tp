package seedu.address.logic.parser.tasks;

import seedu.address.logic.commands.tasks.TaskAddCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new TaskAddCommand object
 */
public class TaskAddCommandParser implements Parser<TaskAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TaskAddCommand
     * and returns an TaskAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public TaskAddCommand parse(String args) throws ParseException {
        return new TaskAddCommand();
    }
}
