package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EXAM_INDEX;

import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListExamTasksCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListExamTasksCommand object.
 */
public class ListExamTasksCommandParser implements Parser<ListExamTasksCommand> {

    private final Pattern pattern = Pattern.compile("(-|\\+)?\\d+(\\.\\d+)?");

    /**
     * Parses the given {@code String} of arguments in the context of the ListExamTasksCommand
     * and returns ListExamTasksCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListExamTasksCommand parse(String args) throws ParseException {
        if (!pattern.matcher(args.strip()).matches()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListExamTasksCommand.MESSAGE_USAGE));
        }

        try {
            Index index = ParserUtil.parseIndex(args);
            return new ListExamTasksCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_EXAM_INDEX);
        }
    }
}
