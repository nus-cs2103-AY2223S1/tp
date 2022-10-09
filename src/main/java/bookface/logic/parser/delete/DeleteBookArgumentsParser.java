package bookface.logic.parser.delete;

import bookface.commons.core.Messages;
import bookface.commons.core.index.Index;
import bookface.logic.commands.delete.DeleteBookCommand;
import bookface.logic.parser.ArgumentsParsable;
import bookface.logic.parser.ParserUtil;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates the relevant new AddCommand object for the relevant entity to be added
 */
public class DeleteBookArgumentsParser implements ArgumentsParsable<DeleteBookCommand> {
    /**
     * Parses the given arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     */
    @Override
    public DeleteBookCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteBookCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteBookCommand.MESSAGE_USAGE), pe);
        }
    }
}
