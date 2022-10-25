package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.logic.commands.task.TaskProgressCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.TaskContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new TaskProgressCommand object
 */
public class TaskProgressCommandParser implements Parser<TaskProgressCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TaskProgressCommand
     * and returns a TaskProgressCommand object for execution.
     *
     * @param args Raw arguments from user.
     * @return TaskProgressCommand object.
     * @throws ParseException If the user input does not conform the expected format
     */
    public TaskProgressCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        List<String> tags = List.of(trimmedArgs.split(" "));

        if (tags.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskProgressCommand.MESSAGE_USAGE));
        }

        return new TaskProgressCommand(new TaskContainsKeywordsPredicate(ParserUtil.parseTags(tags)));
    }
}
