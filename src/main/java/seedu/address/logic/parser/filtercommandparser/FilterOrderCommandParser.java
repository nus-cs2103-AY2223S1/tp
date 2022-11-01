package seedu.address.logic.parser.filtercommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.function.Predicate;

import seedu.address.logic.commands.filtercommands.FilterOrderCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.PredicateParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Order;

/**
 * Parses input arguments and creates a new FilterOrderCommand object.
 */
public class FilterOrderCommandParser implements Parser<FilterOrderCommand> {
    public static final String ADDITIONAL_REQUEST_PREFIX = "o_ar";
    public static final String ORDER_STATUS_PREFIX = "o_st";
    public static final String PRICE_RANGE_PREFIX = "o_pr";

    private static Predicate<Order> defaultPredicate = new Predicate<Order>() {
        @Override
        public boolean test(Order order) {
            return true;
        }
        public boolean equals(Object object) {
            return object instanceof Predicate;
        }
    };

    /**
     * Parses the given {@code String} of arguments in the context of the FilterPetCommand
     * and returns a FilterPetCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FilterOrderCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterOrderCommand.MESSAGE_USAGE));
        }
        String[] nameKeywords = trimmedArgs.split("\\s+");

        Predicate<Order> additionalRequestPredicate = defaultPredicate;
        Predicate<Order> orderStatusPredicate = defaultPredicate;
        Predicate<Order> priceRangePredicate = defaultPredicate;

        for (int i = 0; i < nameKeywords.length; i++) {
            String arg = nameKeywords[i];
            arg = arg.trim();

            switch (arg.substring(0, 4)) {
            case ADDITIONAL_REQUEST_PREFIX:
                additionalRequestPredicate = PredicateParser.parseOrder(arg);
                break;
            case ORDER_STATUS_PREFIX:
                orderStatusPredicate = PredicateParser.parseOrder(arg);
                break;
            case PRICE_RANGE_PREFIX:
                priceRangePredicate = PredicateParser.parseOrder(arg);
                break;
            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterOrderCommand.MESSAGE_USAGE));
            }
        }
        return new FilterOrderCommand(additionalRequestPredicate, orderStatusPredicate, priceRangePredicate);
    }
}
