package seedu.address.logic.parser.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskDescriptor;
import seedu.address.logic.commands.task.UnmarkTaskCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnmarkTaskCommand object
 */
public class UnmarkTaskCommandParser implements Parser<UnmarkTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UnmarkTaskCommand
     * and returns an UnmarkTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnmarkTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Index index;

        try {
            index = ParserUtil.parseIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkTaskCommand.MESSAGE_USAGE), pe);
        }

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        editTaskDescriptor.setIsDone(false);

        return new UnmarkTaskCommand(index, editTaskDescriptor);
    }
}
