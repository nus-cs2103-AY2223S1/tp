package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.RenameCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RenameCommand object.
 */
public class RenameCommandParser implements Parser<RenameCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RenameCommand
     * and returns a RenameCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RenameCommand parse(String args) throws ParseException {
        try {
            String name = ParserUtil.parseRename(args);
            return new RenameCommand(name);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RenameCommand.MESSAGE_USAGE), pe);
        }
    }
}
