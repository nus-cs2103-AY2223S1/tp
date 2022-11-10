package seedu.address.logic.parser.deletecommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.deletecommands.DeleteSupplierCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a {@code DeleteSupplierCommand}.
 */
public class DeleteSupplierCommandParser implements Parser<DeleteSupplierCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteSupplierCommand
     * and returns a DeleteSupplierCommand object for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public DeleteSupplierCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteSupplierCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSupplierCommand.MESSAGE_USAGE), pe);
        }
    }
}
