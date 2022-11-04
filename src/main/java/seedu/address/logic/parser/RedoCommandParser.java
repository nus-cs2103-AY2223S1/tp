package seedu.address.logic.parser;

import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new {@code UndoCommand} object
 */
public class RedoCommandParser implements Parser<RedoCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code UndoCommand}
     * and returns a {@code UndoCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RedoCommand parse(String args) throws ParseException {
        if (!args.isEmpty()) {
            throw new ParseException(RedoCommand.MESSAGE_USAGE);
        }
        return new RedoCommand();
    }
}
