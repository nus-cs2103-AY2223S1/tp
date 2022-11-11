package paymelah.logic.parser;

import static java.util.Objects.requireNonNull;
import static paymelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static paymelah.logic.parser.CliSyntax.PREFIX_DATE;
import static paymelah.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static paymelah.logic.parser.CliSyntax.PREFIX_MONEY;
import static paymelah.logic.parser.CliSyntax.PREFIX_TIME;
import static paymelah.model.debt.DebtTime.DEFAULT_TIME;

import java.util.Set;
import java.util.logging.Logger;

import paymelah.commons.core.LogsCenter;
import paymelah.commons.core.index.Index;
import paymelah.logic.commands.AddDebtCommand;
import paymelah.logic.parser.exceptions.ParseException;
import paymelah.model.debt.Debt;
import paymelah.model.debt.DebtDate;
import paymelah.model.debt.DebtTime;
import paymelah.model.debt.Description;
import paymelah.model.debt.Money;

/**
 * Parses input arguments and creates a new AddDebtCommand object
 */
public class AddDebtCommandParser implements Parser<AddDebtCommand> {
    private static final Logger logger = LogsCenter.getLogger(AddDebtCommandParser.class);
    /**
     * Parses the given {@code String} of arguments in the context of the AddDebtCommand
     * and returns an AddDebtCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddDebtCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_MONEY, PREFIX_DATE, PREFIX_TIME);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_MONEY)) {
            logger.warning("Add Debt command missing prefixes: " + PREFIX_DESCRIPTION + " or " + PREFIX_MONEY);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDebtCommand.MESSAGE_USAGE));
        }

        Set<Index> indices;

        try {
            indices = ParserUtil.parseIndices(argMultimap.getPreamble());
        } catch (ParseException pe) {
            logger.warning("Add Debt command Person index list has items that cannot be parsed to an Index");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDebtCommand.MESSAGE_USAGE), pe);
        }

        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Money money = ParserUtil.parseMoney(argMultimap.getValue(PREFIX_MONEY).get());
        DebtDate date;
        DebtTime time;

        boolean isDatePresent = ParserUtil.arePrefixesPresent(argMultimap, PREFIX_DATE);

        if (isDatePresent) {
            date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        } else {
            date = new DebtDate();
        }

        if (ParserUtil.arePrefixesPresent(argMultimap, PREFIX_TIME)) {
            time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
        } else if (isDatePresent) {
            time = new DebtTime(DEFAULT_TIME);
        } else {
            time = new DebtTime();
        }

        Debt debt = new Debt(description, money, date, time);

        return new AddDebtCommand(indices, debt);
    }
}
