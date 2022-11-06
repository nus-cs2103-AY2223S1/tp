package seedu.boba.logic.parser;

import seedu.boba.commons.core.Messages;
import seedu.boba.logic.commands.RedoCommand;
import seedu.boba.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UndoCommand object
 */
public class RedoCommandParser implements CommandParser<RedoCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UndoCommand
     * and returns a UndoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RedoCommand parse(String args) throws ParseException {
        if (args.isEmpty()) {
            return new RedoCommand();
        } else {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, RedoCommand.MESSAGE_USAGE));
        }
    }
}
