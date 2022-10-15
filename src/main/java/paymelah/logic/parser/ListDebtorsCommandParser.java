package paymelah.logic.parser;

import static paymelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static paymelah.logic.parser.CliSyntax.PREFIX_MONEY;

import paymelah.logic.commands.ListDebtorsCommand;
import paymelah.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListDebtorsCommand object
 */
public class ListDebtorsCommandParser implements Parser<ListDebtorsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListDebtorsCommand
     * and returns an ListDebtorsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListDebtorsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MONEY);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListDebtorsCommand.MESSAGE_USAGE));
        }

        if (ParserUtil.arePrefixesPresent(argMultimap, PREFIX_MONEY)) {
            return new ListDebtorsCommand(
                    ParserUtil.prepareDebtGreaterEqualAmountPredicate(argMultimap.getValue(PREFIX_MONEY).get()));
        }

        return new ListDebtorsCommand();
    }
}
