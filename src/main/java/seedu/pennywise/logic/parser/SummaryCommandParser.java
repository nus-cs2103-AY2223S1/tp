package seedu.pennywise.logic.parser;

import static seedu.pennywise.logic.parser.CliSyntax.PREFIX_MONTH;

import java.time.YearMonth;
import java.util.stream.Stream;

import seedu.pennywise.logic.commands.SummaryCommand;
import seedu.pennywise.logic.parser.exceptions.ParseException;
import seedu.pennywise.model.entry.EntryInYearMonthPredicate;


/**
 * Parses input arguments and create a new {@code SummaryCommand} object.
 */
public class SummaryCommandParser implements Parser<SummaryCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code SummaryCommand}
     * object and returns a {@code SummaryCommand} object for execution.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public SummaryCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MONTH);

        if (arePrefixesPresent(argMultimap, PREFIX_MONTH)) {
            YearMonth month = ParserUtil.parseYearMonth(argMultimap.getValue(PREFIX_MONTH).get());
            EntryInYearMonthPredicate predicate = new EntryInYearMonthPredicate(month);
            return new SummaryCommand(predicate);
        }
        return new SummaryCommand();
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
