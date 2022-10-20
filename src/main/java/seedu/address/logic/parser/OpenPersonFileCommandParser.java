package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.OpenPersonFileCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new OpenPersonFileCommand object
 */
public class OpenPersonFileCommandParser implements Parser<OpenPersonFileCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the OpenPersonFileCommand
     * and returns a OpenPersonFileCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public OpenPersonFileCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new OpenPersonFileCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, OpenPersonFileCommand.MESSAGE_USAGE), pe);
        }
    }
}
