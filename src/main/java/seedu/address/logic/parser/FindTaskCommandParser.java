package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindTasksCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.DescriptionContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindTaskCommand object
 */
public class FindTaskCommandParser implements Parser<FindTasksCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindTaskCommand
     * and returns a FindTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTasksCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTasksCommand.MESSAGE_USAGE));
        }
        return new FindTasksCommand(new DescriptionContainsKeywordsPredicate(Arrays.asList(trimmedArgs)));
    }
}
