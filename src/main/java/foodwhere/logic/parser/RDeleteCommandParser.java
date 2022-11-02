package foodwhere.logic.parser;

import static foodwhere.logic.commands.RDeleteCommand.MESSAGE_INVALID_INDEX_ERROR;

import foodwhere.commons.core.index.Index;
import foodwhere.logic.commands.RDeleteCommand;
import foodwhere.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RDeleteCommand object
 */
public class RDeleteCommandParser implements Parser<RDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RDeleteCommand
     * and returns a RDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RDeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new RDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_INDEX_ERROR);
        }
    }

}
