package tracko.logic.parser.order;

import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tracko.logic.parser.CliSyntax.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.sun.jdi.connect.Connector;
import tracko.logic.commands.order.FindOrderCommand;
import tracko.logic.parser.*;
import tracko.logic.parser.exceptions.ParseException;
import tracko.model.order.OrderContainsKeywordsPredicate;
import tracko.model.order.OrderDeliveredOrPaidPredicate;

/**
 * Parses input arguments and creates a new FindOrderCommand object
 */
public class FindOrderCommandParser implements Parser<FindOrderCommand> {
    private static final String DELIMITER = "\\s+";

    /**
     * Parses the given {@code String} of arguments in the context of the FindOrderCommand
     * and returns a FindOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindOrderCommand parse(String userInput) throws ParseException {
        ArgumentMultimap prefixMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_ADDRESS,
                PREFIX_ITEM);
        ArgumentMultimap flagMultimap = FlagTokenizer.tokenize(userInput, FLAG_PAID, FLAG_UNPAID,
                FLAG_DELIVERED, FLAG_UNDELIVERED);

        boolean isAnyPrefixPresent = isAnyPrefixPresent(prefixMultimap, PREFIX_NAME, PREFIX_ADDRESS,
                PREFIX_ITEM);
        boolean isAnyFlagPresent = isAnyFlagPresent(flagMultimap, FLAG_PAID, FLAG_UNPAID,
                FLAG_DELIVERED, FLAG_UNDELIVERED);
        boolean isPrefixMultimapPreambleEmpty = prefixMultimap.getPreamble().isEmpty();
        boolean isFlagMultimapPreampleEmpty = flagMultimap.getPreamble().isEmpty();

        if (!(isAnyPrefixPresent || isAnyFlagPresent)
                || !(isFlagMultimapPreampleEmpty || isPrefixMultimapPreambleEmpty)) {
            throw new ParseException(String.format(FindOrderCommand.MESSAGE_USAGE, FindOrderCommand.MESSAGE_USAGE));
        }

        String nameKeywordsString = prefixMultimap.getValue(PREFIX_NAME).orElse("").trim();
        String addressKeywordsString = prefixMultimap.getValue(PREFIX_ADDRESS).orElse("").trim();
        String itemKeywordsString = prefixMultimap.getValue(PREFIX_ITEM).orElse("").trim();

        String [] nameKeywords =  splitKeywords(nameKeywordsString);
        String[] addressKeywords = splitKeywords(addressKeywordsString);
        String[] itemKeywords = splitKeywords(itemKeywordsString);
        OrderContainsKeywordsPredicate prefixPredicate = new OrderContainsKeywordsPredicate(Arrays.asList(nameKeywords),
                Arrays.asList(addressKeywords),
                Arrays.asList(itemKeywords));

        // if -d => delivered only
        // if -p => paid only
        // if -D => undelivered only
        // if -P => unpaid only
        // by the end of parsing, need to have 4 booleans

        boolean isPaid = flagMultimap.getValue(FLAG_PAID).orElse("false").equals("true");
        boolean isDelivered = flagMultimap.getValue(FLAG_DELIVERED).orElse("false").equals("true");
        boolean isUnpaid = flagMultimap.getValue(FLAG_UNPAID).orElse("false").equals("true");
        boolean isUndelivered = flagMultimap.getValue(FLAG_UNDELIVERED).orElse("false").equals("true");

        boolean isFilteringByPaid = isPaid || isUnpaid;
        boolean isFilterByDelivered = isDelivered || isUndelivered;

        OrderDeliveredOrPaidPredicate flagPredicate = new OrderDeliveredOrPaidPredicate(isFilteringByPaid,
                isFilterByDelivered, isPaid, isDelivered);

        return new FindOrderCommand(prefixPredicate, flagPredicate);
    }

    private static boolean isAnyPrefixPresent(ArgumentMultimap prefixMap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> prefixMap.getValue(prefix).isPresent());
    }

    public static boolean isAnyFlagPresent(ArgumentMultimap flagMap, Flag... flags) {
        return Stream.of(flags).anyMatch(flag -> flagMap.getValue(flag).isPresent());
    }

    /**
     * Splits keywords by {@code DELIMITER}.
     * @param keywords keywords for a certain prefix.
     * @return An empty String[] when the input is an empty String,
     *          otherwise returns a String[] containing keywords.
     */
    private static String[] splitKeywords(String keywords) {
        if (keywords.equals("")) {
            return new String[0];
        }

        return keywords.split(DELIMITER);
    }
}
