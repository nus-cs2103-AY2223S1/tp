package paymelah.logic.parser;

import static java.util.Objects.requireNonNull;
import static paymelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static paymelah.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static paymelah.logic.parser.CliSyntax.PREFIX_MONEY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import paymelah.commons.core.index.Index;
import paymelah.logic.commands.SplitDebtCommand;
import paymelah.logic.parser.exceptions.ParseException;
import paymelah.model.debt.Debt;
import paymelah.model.debt.Description;
import paymelah.model.debt.Money;

/**
 * Parses input arguments and creates a new SplitDebtCommand object
 */
public class SplitDebtCommandParser implements Parser<SplitDebtCommand> {

    private static final String SELF_INDEX = "0";

    /**
     * Parses the given {@code String} of arguments in the context of the SplitDebtCommand
     * and returns an SplitDebtCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public SplitDebtCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_MONEY);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_MONEY)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SplitDebtCommand.MESSAGE_USAGE));
        }

        boolean isSelfPresent;
        Set<Index> nonSelfIndexList;

        try {
            List<String> indexList = Arrays.stream(argMultimap.getPreamble().split(" "))
                    .collect(Collectors.toList());
            isSelfPresent = indexList.removeAll(Collections.singleton(SELF_INDEX));
            nonSelfIndexList = ParserUtil.parseIndexes(indexList);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SplitDebtCommand.MESSAGE_USAGE), pe);
        }

        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Money money = ParserUtil.parseMoney(argMultimap.getValue(PREFIX_MONEY).get());

        int debtorCount = nonSelfIndexList.size();
        if (debtorCount == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SplitDebtCommand.MESSAGE_USAGE));
        }

        int divisor = isSelfPresent ? debtorCount + 1 : debtorCount;
        Money splitMoney = money.splitValue(divisor);
        Debt debt = new Debt(description, splitMoney);

        return new SplitDebtCommand(nonSelfIndexList, debt);
    }
}
