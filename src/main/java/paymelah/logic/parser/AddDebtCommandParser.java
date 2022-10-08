package paymelah.logic.parser;

import static java.util.Objects.requireNonNull;
import static paymelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static paymelah.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static paymelah.logic.parser.CliSyntax.PREFIX_MONEY;

import java.util.stream.Stream;

import paymelah.commons.core.index.Index;
import paymelah.logic.commands.AddDebtCommand;
import paymelah.logic.parser.exceptions.ParseException;
import paymelah.model.debt.Debt;
import paymelah.model.debt.Description;
import paymelah.model.debt.Money;

/**
 * Parses input arguments and creates a new AddDebtCommand object
 */
public class AddDebtCommandParser implements Parser<AddDebtCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddDebtCommand
     * and returns an AddDebtCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddDebtCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_MONEY);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_MONEY)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDebtCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDebtCommand.MESSAGE_USAGE), pe);
        }

        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Money money = ParserUtil.parseMoney(argMultimap.getValue(PREFIX_MONEY).get());

        Debt debt = new Debt(description, money);

        return new AddDebtCommand(index, debt);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
