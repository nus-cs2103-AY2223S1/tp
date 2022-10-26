package paymelah.logic.parser;

import static paymelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static paymelah.logic.parser.CliSyntax.PREFIX_MONEY;
import static paymelah.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Comparator;

import paymelah.logic.commands.SortCommand;
import paymelah.logic.parser.exceptions.ParseException;
import paymelah.model.person.NameComparator;
import paymelah.model.person.Person;
import paymelah.model.person.TotalDebtAmountComparator;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    public static final String CRITERIA_MONEY = "total amount owed";
    public static final String CRITERIA_NAME = "name";
    public static final String ORDER_ASCENDING = "ascending order";
    public static final String ORDER_DESCENDING = "descending order";

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MONEY);

        if (!ParserUtil.isExactlyOneOfPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_MONEY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        Comparator<Person> comparator = new NameComparator();
        String criteria = CRITERIA_NAME;
        String order = ORDER_ASCENDING;

        if (ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            String arg = argMultimap.getValue(PREFIX_NAME).get().trim();
            switch (arg) {
            case "+":
                //default assignments are already correct
                break;
            case "-":
                comparator = new NameComparator().reversed();
                order = ORDER_DESCENDING;
                break;
            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
            }
        }

        if (ParserUtil.arePrefixesPresent(argMultimap, PREFIX_MONEY)) {
            String arg = argMultimap.getValue(PREFIX_MONEY).get().trim();
            criteria = CRITERIA_MONEY;
            switch (arg) {
            case "+":
                comparator = new TotalDebtAmountComparator();
                order = ORDER_ASCENDING;
                break;
            case "-":
                comparator = new TotalDebtAmountComparator().reversed();
                order = ORDER_DESCENDING;
                break;
            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
            }
        }

        return new SortCommand(comparator, criteria, order);
    }

}
