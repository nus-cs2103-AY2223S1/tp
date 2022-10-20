
package longtimenosee.logic.parser;

import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import longtimenosee.commons.core.index.Index;
import longtimenosee.logic.commands.ViewIncomeCommand;
import longtimenosee.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewIncomeCommand object
 */
public class ViewIncomeCommandParser {
    /**
     * Parses the viewIncome in the context of the ViewPIncome Command
     * and returns a ViewIncomeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewIncomeCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewIncomeCommand(index.getOneBased());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewIncomeCommand.MESSAGE_USAGE), pe);
        }
    }
}
