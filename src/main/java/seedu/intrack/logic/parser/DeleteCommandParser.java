package seedu.intrack.logic.parser;

import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.intrack.commons.core.index.Index;
import seedu.intrack.logic.commands.DeleteCommand;
import seedu.intrack.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code DeleteCommand} object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code DeleteCommand}
     * and returns a {@code DeleteCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            assert index.getOneBased() > 0 : "index should be a positive unsigned integer";
            return new DeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
