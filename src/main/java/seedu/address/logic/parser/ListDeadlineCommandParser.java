package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.logic.commands.list.ListDeadlineCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.TaskByDeadlinePredicate;

/**
 * Parses input arguments and creates a new ListDeadlineCommand object
 */
public class ListDeadlineCommandParser implements Parser<ListDeadlineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListDeadlineCommand
     * and returns a ListDeadlineCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListDeadlineCommand parse(String args) throws ParseException {
        String[] listTypes = args.split(" ", 2);

        if (listTypes.length < 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, Deadline.MESSAGE_CONSTRAINTS));
        }

        return new ListDeadlineCommand(getPredicate(listTypes[1]));
    }

    public TaskByDeadlinePredicate getPredicate(String keywords) throws ParseException {
        ParserUtil.parseDeadline(keywords);

        return new TaskByDeadlinePredicate(List.of(keywords));
    }
}
