package bookface.logic.parser;

import bookface.commons.core.Messages;
import bookface.commons.core.index.Index;
import bookface.logic.commands.DeleteUserCommand;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteUserCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteUserCommand parse(String args) throws ParseException {
        try {
            String trimmedString = args.trim();
            String[] stringArray = trimmedString.split("\\s+");
            String keyWord = stringArray[0];

            if (stringArray.length < 2) {
                throw new ParseException(
                        String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteUserCommand.MESSAGE_USAGE));
            }

            Index index = ParserUtil.parseIndex(stringArray[1]);

            if (keyWord.equals("user")) {
                return new DeleteUserCommand(index);
            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteUserCommand.MESSAGE_USAGE), pe);
        }
        throw new ParseException(
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteUserCommand.MESSAGE_USAGE));
    }

}
