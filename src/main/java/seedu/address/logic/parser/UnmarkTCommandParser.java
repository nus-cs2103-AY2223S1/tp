package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnmarkTCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnmarkTCommand object
 */
public class UnmarkTCommandParser implements Parser<UnmarkTCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UnmarkTCommand
     * and returns an UnmarkTCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnmarkTCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnmarkTCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkTCommand.MESSAGE_USAGE), pe);
        }
    }
}
