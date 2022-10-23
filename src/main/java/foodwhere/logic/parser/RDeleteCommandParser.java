package foodwhere.logic.parser;

import static foodwhere.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

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
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, pe.getMessage())
                            + RDeleteCommand.MESSAGE_USAGE);
        }
    }

}
