package bookface.logic.parser.delete;

import bookface.commons.core.Messages;
import bookface.commons.core.index.Index;
import bookface.logic.commands.delete.DeleteUserCommand;
import bookface.logic.parser.ArgumentsParsable;
import bookface.logic.parser.ParserUtil;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates the relevant new DeleteCommand object for the relevant entity to be added
 */
public class DeleteUserArgumentsParser implements ArgumentsParsable<DeleteUserCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteUserCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteUserCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteUserCommand.MESSAGE_USAGE), pe);
        }
    }
}
