package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import seedu.uninurse.logic.commands.TasksOnCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;
import seedu.uninurse.model.task.DateTime;

/**
 * Parses input arguments and creates a new TasksOnCommand object.
 */
public class TasksOnCommandParser implements Parser<TasksOnCommand> {
    /**
     * Parses the given String of arguments in the context of the TasksOnCommand
     * and returns an TasksOnCommand object for execution.
     *
     * @param args The given user input to be parsed.
     * @return TasksOnCommand.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public TasksOnCommand parse(String args) throws ParseException {
        requireAllNonNull(args);
        String date = args.trim();

        if (!DateTime.isValidDate(date)) {
            throw new ParseException(DateTime.MESSAGE_CONSTRAINTS);
        }

        return new TasksOnCommand(DateTime.ofDate(date));
    }
}
