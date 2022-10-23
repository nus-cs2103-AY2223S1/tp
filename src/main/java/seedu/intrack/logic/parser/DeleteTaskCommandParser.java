package seedu.intrack.logic.parser;

import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.intrack.commons.core.index.Index;
import seedu.intrack.logic.commands.DeleteTaskCommand;
import seedu.intrack.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code DeleteTaskCommand} object
 */
public class DeleteTaskCommandParser implements Parser<DeleteTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code DeleteTaskCommand}
     * and returns a {@code DeleteTaskCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTaskCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteTaskCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE), pe);
        }
    }
}
