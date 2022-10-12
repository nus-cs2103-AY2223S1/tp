package paymelah.logic.parser;

import static paymelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static paymelah.logic.parser.ParserUtil.parseDebtContainsKeywordsPredicate;

import paymelah.logic.commands.FindDebtCommand;
import paymelah.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@link FindDebtCommand} object
 */
public class FindDebtCommandParser implements Parser<FindDebtCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * {@link FindDebtCommand}
     * and returns a {@link FindDebtCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindDebtCommand parse(String args) throws ParseException {
        try {
            return new FindDebtCommand(parseDebtContainsKeywordsPredicate(args));
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDebtCommand.MESSAGE_USAGE));
        }
    }

}
