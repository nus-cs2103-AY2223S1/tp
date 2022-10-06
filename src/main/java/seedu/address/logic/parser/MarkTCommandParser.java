package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkTCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkTCommand object
 */
public class MarkTCommandParser implements Parser<MarkTCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the MarkTCommand
     * and returns a MarkTCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkTCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MarkTCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkTCommand.MESSAGE_USAGE), pe);
        }
    }
}
