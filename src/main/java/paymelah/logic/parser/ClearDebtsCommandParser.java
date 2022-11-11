package paymelah.logic.parser;

import static paymelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import paymelah.commons.core.index.Index;
import paymelah.logic.commands.ClearDebtsCommand;
import paymelah.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ClearDebtsCommand object
 */
public class ClearDebtsCommandParser implements Parser<ClearDebtsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClearDebtsCommand
     * and returns a ClearDebtsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearDebtsCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ClearDebtsCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearDebtsCommand.MESSAGE_USAGE), pe);
        }
    }

}
