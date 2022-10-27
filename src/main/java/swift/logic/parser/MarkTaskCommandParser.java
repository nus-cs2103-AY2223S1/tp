package swift.logic.parser;

import static swift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import swift.commons.core.index.Index;
import swift.logic.commands.MarkTaskCommand;
import swift.logic.parser.exceptions.ParseException;

public class MarkTaskCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the MarkTaskCommand and
     * returns a MarkTaskCommand object for execution.
     * 
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkTaskCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MarkTaskCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkTaskCommand.MESSAGE_USAGE), pe);
        }
    }
}
