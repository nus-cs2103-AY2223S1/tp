package swift.logic.parser;

import swift.commons.core.index.Index;
import swift.logic.commands.SelectContactCommand;
import swift.logic.commands.SelectTaskCommand;
import swift.logic.parser.exceptions.ParseException;

import static swift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new SelectTaskCommand object
 */
public class SelectTaskCommandParser implements Parser<SelectTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SelectTaskCommand
     * and returns a SelectTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SelectTaskCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new SelectTaskCommand(index);
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectContactCommand.MESSAGE_USAGE), e);
        }
    }
}
