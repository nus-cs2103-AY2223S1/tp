package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import jarvis.commons.core.index.Index;
import jarvis.logic.commands.MarkTaskCommand;
import jarvis.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkTaskCommand object
 */
public class MarkTaskCommandParser implements Parser<MarkTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the MarkTaskCommand
     * and returns a MarkTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkTaskCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MarkTaskCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkTaskCommand.MESSAGE_USAGE), pe);
        }
    }

}

