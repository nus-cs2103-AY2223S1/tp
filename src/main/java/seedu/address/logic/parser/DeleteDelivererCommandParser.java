package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteDelivererCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteDelivererCommand object.
 */
public class DeleteDelivererCommandParser implements Parser<DeleteDelivererCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteDelivererCommand
     * and returns a DeleteDelivererCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteDelivererCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteDelivererCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDelivererCommand.MESSAGE_USAGE), pe);
        }
    }
}
