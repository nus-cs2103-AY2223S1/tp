package tracko.logic.parser.item;

import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import tracko.commons.core.index.Index;
import tracko.logic.commands.item.DeleteItemCommand;
import tracko.logic.parser.Parser;
import tracko.logic.parser.ParserUtil;
import tracko.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteItemCommand object
 */
public class DeleteItemCommandParser implements Parser<DeleteItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteItemCommand
     * and returns a DeleteItemCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteItemCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteItemCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteItemCommand.MESSAGE_USAGE), pe);
        }
    }

}
