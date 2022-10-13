package seedu.address.logic.parser.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskDescriptor;
import seedu.address.logic.commands.task.MarkTaskCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkTaskCommand object
 */
public class MarkTaskCommandParser implements Parser<MarkTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the MarkTaskCommand
     * and returns a MarkTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Index index;

        try {
            index = ParserUtil.parseIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkTaskCommand.MESSAGE_USAGE), pe);
        }

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        editTaskDescriptor.setIsDone(true);

        return new MarkTaskCommand(index, editTaskDescriptor);
    }
}
