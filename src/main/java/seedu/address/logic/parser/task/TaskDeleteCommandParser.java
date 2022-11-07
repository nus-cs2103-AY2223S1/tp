package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.TaskDeleteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
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
    public TaskDeleteCommand parse(String args) throws ParseException {
        try {
            Index[] indexes = ParserUtil.parseIndexes(args);
            return new TaskDeleteCommand(indexes);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskDeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
