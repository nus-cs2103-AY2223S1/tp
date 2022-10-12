package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.team.Task;


/**
 * Parses input arguments and creates a new AddTaskCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns a AddTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        String name = args.trim();
        if (!Task.isValidName(name)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddTaskCommand.MESSAGE_TASK_NAME_FORMAT_ERROR));
        }
        return new AddTaskCommand(name);
    }
}
