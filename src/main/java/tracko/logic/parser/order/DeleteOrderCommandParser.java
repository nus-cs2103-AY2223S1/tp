package tracko.logic.parser.order;

import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import tracko.commons.core.index.Index;
import tracko.logic.commands.order.DeleteOrderCommand;
import tracko.logic.parser.Parser;
import tracko.logic.parser.ParserUtil;
import tracko.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteOrderCommandParser implements Parser<DeleteOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteOrderCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteOrderCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteOrderCommand.MESSAGE_USAGE), pe);
        }
    }

}
