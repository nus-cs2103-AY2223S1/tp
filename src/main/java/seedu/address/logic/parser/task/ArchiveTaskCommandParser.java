package seedu.address.logic.parser.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskDescriptor;
import seedu.address.logic.commands.task.ArchiveTaskCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.ArchivalStatus;

/**
 * Parses input arguments and creates a new ArchiveTaskCommand object
 */
public class ArchiveTaskCommandParser implements Parser<ArchiveTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ArchiveTaskCommand
     * and returns a ArchiveTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ArchiveTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Index index;

        try {
            index = ParserUtil.parseIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ArchiveTaskCommand.MESSAGE_USAGE), pe);
        }

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        editTaskDescriptor.setArchivalStatus(new ArchivalStatus(true));

        return new ArchiveTaskCommand(index, editTaskDescriptor);
    }
}
