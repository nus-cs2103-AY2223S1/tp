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
import paymelah.logic.commands.SplitDebtCommand;
import paymelah.logic.parser.exceptions.ParseException;
import paymelah.model.debt.Debt;
import paymelah.model.debt.DebtDate;
import paymelah.model.debt.DebtTime;
import paymelah.model.debt.Description;
import paymelah.model.debt.Money;

/**
 * Parses input arguments and creates a new SplitDebtCommand object
 */
public class SplitDebtCommandParser implements Parser<SplitDebtCommand> {
    private static final Logger logger = LogsCenter.getLogger(SplitDebtCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the SplitDebtCommand
     * and returns an SplitDebtCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public SplitDebtCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_MONEY, PREFIX_DATE, PREFIX_TIME);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_MONEY)) {
            logger.warning("Split Debt command missing prefixes: " + PREFIX_DESCRIPTION + " or " + PREFIX_MONEY);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SplitDebtCommand.MESSAGE_USAGE));
        }

        String preamble = argMultimap.getPreamble();
        boolean isSelfAtStart = preamble.startsWith("0 ");
        boolean isSelfAtMiddle = preamble.contains(" 0 ");
        boolean isSelfAtEnd = preamble.endsWith(" 0");
        boolean isSelfPresent = isSelfAtStart || isSelfAtMiddle || isSelfAtEnd;

        while (isSelfAtMiddle) {
            preamble = preamble.replace(" 0 ", " ");
            isSelfAtMiddle = preamble.contains(" 0 ");
        }

        if (isSelfAtStart) {
            preamble = preamble.substring(2);
        }
        if (isSelfAtEnd) {
            preamble = preamble.substring(0, preamble.length() - 2);
        }

        Set<Index> nonSelfIndexList;

        try {
            nonSelfIndexList = ParserUtil.parseIndices(preamble);
        } catch (ParseException pe) {
            logger.warning("Split Debt command Person index list has items that cannot be parsed to an Index");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SplitDebtCommand.MESSAGE_USAGE), pe);
        }

        if (isSelfPresent) {
            logger.info("Split Debt command Person index list contains an index referencing the user");
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

        int debtorCount = nonSelfIndexList.size();
        if (debtorCount == 0) {
            logger.warning("Split Debt command Person index list does not refer to non-user Persons");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SplitDebtCommand.MESSAGE_USAGE));
        }

        int divisor = isSelfPresent ? debtorCount + 1 : debtorCount;
        logger.info("Split Debt command is splitting $" + money + " " + divisor + "-ways");
        Money splitMoney = money.splitValue(divisor);
        Debt debt = new Debt(description, splitMoney, date, time);
        logger.info("Split Debt command is creating Debt:" + debt);
        return new SplitDebtCommand(nonSelfIndexList, debt);
    }
}
