package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.logic.commands.task.FilterTaskCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.TaskContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FilterTaskCommandParser object
 */
public class FilterTaskCommandParser implements Parser<FilterTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterTaskCommand
     * and returns a FilterTaskCommand object for execution.
     *
     * @param args Raw arguments from user.
     * @return FilterTaskCommand object.
     * @throws ParseException If the user input does not conform the expected format
     */
    public FilterTaskCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        List<String> tags = List.of(trimmedArgs.split(" "));

        if (tags.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTaskCommand.MESSAGE_USAGE));
        }

        return new FilterTaskCommand(new TaskContainsKeywordsPredicate(ParserUtil.parseTags(tags)));
    }
}
