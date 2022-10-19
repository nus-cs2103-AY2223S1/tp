package seedu.uninurse.logic.parser;

import seedu.uninurse.logic.commands.TasksOnCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;
import seedu.uninurse.model.task.DateTime;

/**
 * Parses input arguments and creates a new TasksOnCommand object.
 */
public class TasksOnCommandParser implements Parser<TasksOnCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TasksOnCommand
     * and returns an TasksOnCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public TasksOnCommand parse(String args) throws ParseException {
        String date = args.trim();

        if (!DateTime.isValidDate(date)) {
            throw new ParseException(DateTime.MESSAGE_CONSTRAINTS);
        }

        return new TasksOnCommand(DateTime.ofDate(date));
    }
}
