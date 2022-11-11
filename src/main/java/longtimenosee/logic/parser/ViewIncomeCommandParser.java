
package longtimenosee.logic.parser;

import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_YEAR_FORMAT;

import longtimenosee.commons.core.index.Index;
import longtimenosee.logic.commands.ViewIncomeCommand;
import longtimenosee.logic.parser.exceptions.InvalidYearException;
import longtimenosee.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewIncomeCommand object
 */
public class ViewIncomeCommandParser implements Parser<ViewIncomeCommand> {
    /**
     * Parses the viewIncome in the context of the ViewPIncome Command
     * and returns a ViewIncomeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewIncomeCommand parse(String args) throws ParseException {
        try {
            args = args.replaceAll(" ", "");
            Index index = ParserUtil.parseIndex(args);
            if (index.getOneBased() < 1900 || index.getOneBased() > 2100) {
                throw new InvalidYearException("Chosen year is invalid");
            }
            return new ViewIncomeCommand(index.getOneBased());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewIncomeCommand.MESSAGE_USAGE), pe);
        } catch (InvalidYearException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_YEAR_FORMAT, ViewIncomeCommand.MESSAGE_USAGE), e);
        }
    }
}
