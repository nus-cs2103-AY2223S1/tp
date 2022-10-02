package bookface.logic.parser;

import bookface.commons.core.Messages;
import bookface.logic.commands.ListUsersCommand;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class ListCommandParser implements Parser<ListUsersCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListUsersCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] stringArray = trimmedArgs.split("\\s+");
        String keyWord = stringArray[0];

        if (keyWord.equals("users")) {
            return new ListUsersCommand();
        }
        throw new ParseException(
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ListUsersCommand.MESSAGE_USAGE));
    }

}
