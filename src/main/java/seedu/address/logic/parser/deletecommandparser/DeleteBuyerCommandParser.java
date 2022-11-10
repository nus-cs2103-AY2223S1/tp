package seedu.address.logic.parser.deletecommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.deletecommands.DeleteBuyerCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a {@code DeleteBuyerCommand}.
 */
public class DeleteBuyerCommandParser implements Parser<DeleteBuyerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteBuyerCommand
     * and returns a DeleteBuyerCommand object for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public DeleteBuyerCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteBuyerCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBuyerCommand.MESSAGE_USAGE), pe);
        }
    }
}
