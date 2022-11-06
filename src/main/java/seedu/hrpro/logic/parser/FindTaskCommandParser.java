package seedu.hrpro.logic.parser;

import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.hrpro.logic.commands.FindTaskCommand;
import seedu.hrpro.logic.parser.exceptions.ParseException;
import seedu.hrpro.model.task.TaskDescription;
import seedu.hrpro.model.task.TaskDescriptionContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindTaskCommand object
 */
public class FindTaskCommandParser implements Parser<FindTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTaskCommand
     * and returns a FindTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTaskCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");

        for (String keyword : keywords) {
            if (!TaskDescription.isValidTaskDescription(keyword)) {
                throw new ParseException(TaskDescription.MESSAGE_CONSTRAINTS);
            }
        }

        return new FindTaskCommand(new TaskDescriptionContainsKeywordsPredicate(Arrays.asList(keywords)));
    }

}
