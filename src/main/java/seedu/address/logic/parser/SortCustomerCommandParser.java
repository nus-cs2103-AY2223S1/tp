package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_UNIQUE_COMPARATOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_CUSTOMER_ACTIVE_COMMISSION_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_CUSTOMER_COMMISSION_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_CUSTOMER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_CUSTOMER_REVENUE;
import static seedu.address.logic.parser.ParserUtil.areAnyPrefixesPresent;
import static seedu.address.logic.parser.ParserUtil.countPrefixesPresent;
import static seedu.address.logic.parser.ParserUtil.parseSortDirection;
import static seedu.address.model.Model.CUSTOMER_NAME_COMPARATOR;
import static seedu.address.model.Model.CUSTOMER_NUM_ACTIVE_COMMISSIONS_COMPARATOR;
import static seedu.address.model.Model.CUSTOMER_NUM_COMMISSIONS_COMPARATOR;
import static seedu.address.model.Model.CUSTOMER_REVENUE_COMPARATOR;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javafx.util.Pair;
import seedu.address.logic.commands.SortCustomerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.customer.Customer;
import seedu.address.model.util.SortDirection;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class SortCustomerCommandParser implements Parser<SortCustomerCommand> {

    public static final Map<Prefix, Pair<String, Comparator<Customer>>> PREFIX_DESCRIPTION_MAP;

    static {
        PREFIX_DESCRIPTION_MAP = new HashMap<>();
        PREFIX_DESCRIPTION_MAP.put(PREFIX_SORT_CUSTOMER_NAME, new Pair<>("name", CUSTOMER_NAME_COMPARATOR));
        PREFIX_DESCRIPTION_MAP.put(PREFIX_SORT_CUSTOMER_COMMISSION_COUNT,
                new Pair<>("commission count", CUSTOMER_NUM_COMMISSIONS_COMPARATOR));
        PREFIX_DESCRIPTION_MAP.put(PREFIX_SORT_CUSTOMER_ACTIVE_COMMISSION_COUNT,
                new Pair<>("active commissions count", CUSTOMER_NUM_ACTIVE_COMMISSIONS_COMPARATOR));
        PREFIX_DESCRIPTION_MAP.put(PREFIX_SORT_CUSTOMER_REVENUE,
                new Pair<>("revenue", CUSTOMER_REVENUE_COMPARATOR));
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCustomerCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Prefix[] allPrefixes = PREFIX_DESCRIPTION_MAP.keySet().toArray(new Prefix[0]);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, allPrefixes);
        if (!areAnyPrefixesPresent(argMultimap, allPrefixes)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCustomerCommand.MESSAGE_USAGE));
        }


        if (countPrefixesPresent(argMultimap, allPrefixes) != 1) {
            throw new ParseException(MESSAGE_INVALID_UNIQUE_COMPARATOR);
        }
        Comparator<Customer> comparator = (c1, c2) -> 0;
        String comparatorDescription = "";
        SortDirection direction = new SortDirection("+");
        for (Prefix key: PREFIX_DESCRIPTION_MAP.keySet()) {
            Optional<String> suffix = argMultimap.getValue(key);
            if (suffix.isEmpty()) {
                continue;
            }
            Pair<String, Comparator<Customer>> comparatorEntry = PREFIX_DESCRIPTION_MAP.get(key);
            comparatorDescription = comparatorEntry.getKey();
            comparator = comparatorEntry.getValue();
            direction = parseSortDirection(suffix.get());
            if (!direction.isIncreasing()) {
                comparator = comparator.reversed();
            }
            break;
        }
        return new SortCustomerCommand(comparator, comparatorDescription, direction);
    }

}
