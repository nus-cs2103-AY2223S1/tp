package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTaCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteTaCommand object
 */
public class DeleteTaCommandParser implements Parser<DeleteTaCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTaCommand
     * and returns a DeleteTaCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTaCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteTaCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaCommand.MESSAGE_USAGE), pe);
        }
    }
}
