package paymelah.logic.parser;

import static paymelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import paymelah.logic.commands.FindDebtCommand;
import paymelah.logic.parser.exceptions.ParseException;
import paymelah.model.person.DebtContainsKeywordsPredicate;

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
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDebtCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindDebtCommand(new DebtContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
