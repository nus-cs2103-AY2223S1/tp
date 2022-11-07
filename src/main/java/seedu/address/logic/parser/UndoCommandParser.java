package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new {@code UndoCommand} object
 */
public class UndoCommandParser implements Parser<UndoCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code UndoCommand}
     * and returns a {@code UndoCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format -- expected: empty
     */
    public UndoCommand parse(String args) throws ParseException {
        if (!args.isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_USAGE));
        }
        return new UndoCommand();
    }
}
