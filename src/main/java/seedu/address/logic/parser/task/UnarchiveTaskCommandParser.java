package seedu.address.logic.parser.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskDescriptor;
import seedu.address.logic.commands.task.UnarchiveTaskCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.ArchivalStatus;

/**
 * Parses input arguments and creates a new UnarchiveTaskCommand object
 */
public class UnarchiveTaskCommandParser implements Parser<UnarchiveTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UnarchiveTaskCommand
     * and returns a UnarchiveTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnarchiveTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Index index;

        try {
            index = ParserUtil.parseIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnarchiveTaskCommand.MESSAGE_USAGE), pe);
        }

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        editTaskDescriptor.setArchivalStatus(new ArchivalStatus(false));

        return new UnarchiveTaskCommand(index, editTaskDescriptor);
    }
}
