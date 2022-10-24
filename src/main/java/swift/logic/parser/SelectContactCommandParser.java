package swift.logic.parser;

import static swift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import swift.commons.core.index.Index;
import swift.logic.commands.SelectContactCommand;
import swift.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class SelectContactCommandParser implements Parser<SelectContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SelectContactCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new SelectContactCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectContactCommand.MESSAGE_USAGE), pe);
        }
    }

}
