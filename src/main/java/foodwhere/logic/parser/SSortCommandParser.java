package foodwhere.logic.parser;

import static foodwhere.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import foodwhere.logic.commands.SSortCommand;
import foodwhere.logic.parser.exceptions.ParseException;
import foodwhere.model.stall.comparator.StallsComparatorList;

/**
 * Parses input arguments and creates a new SSortCommand object
 */
public class SSortCommandParser implements Parser<SSortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SSortCommand
     * and returns a SSortCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SSortCommand parse(String args) throws ParseException {
        try {
            StallsComparatorList stallsComparator = ParserUtil.parseStallCriteria(args);
            return new SSortCommand(stallsComparator);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SSortCommand.MESSAGE_USAGE), pe);
        }
    }
}
