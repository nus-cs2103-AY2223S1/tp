package seedu.address.logic.parser.filtercommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_ADDITIONAL_REQUESTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_STATUS;

import java.util.function.Predicate;

import seedu.address.logic.commands.filtercommands.FilterOrderCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.PredicateParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Order;

/**
 * Parses input arguments and creates a new FilterOrderCommand object.
 */
public class FilterOrderCommandParser implements Parser<FilterOrderCommand> {

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
    public FilterOrderCommand parse(String trimmedArgs) throws ParseException {

        boolean isTokenized = false;

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterOrderCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(trimmedArgs, PREFIX_ORDER_ADDITIONAL_REQUESTS, PREFIX_ORDER_STATUS,
                        PREFIX_ORDER_PRICE_RANGE);

        Predicate<Order> additionalRequestPredicate = defaultPredicate;
        Predicate<Order> orderStatusPredicate = defaultPredicate;
        Predicate<Order> priceRangePredicate = defaultPredicate;

        if (argMultimap.getValue(PREFIX_ORDER_ADDITIONAL_REQUESTS).isPresent()) {
            additionalRequestPredicate = PredicateParser.parseOrder(argMultimap
                    .getValue(PREFIX_ORDER_ADDITIONAL_REQUESTS).get(), PREFIX_ORDER_ADDITIONAL_REQUESTS.getPrefix());
            isTokenized = true;
        }

        if (argMultimap.getValue(PREFIX_ORDER_STATUS).isPresent()) {
            orderStatusPredicate = PredicateParser.parseOrder(argMultimap.getValue(PREFIX_ORDER_STATUS).get(),
                    PREFIX_ORDER_STATUS.getPrefix());
            isTokenized = true;
        }

        if (argMultimap.getValue(PREFIX_ORDER_PRICE_RANGE).isPresent()) {
            priceRangePredicate = PredicateParser.parseOrder(argMultimap.getValue(PREFIX_ORDER_PRICE_RANGE).get(),
                    PREFIX_ORDER_PRICE_RANGE.getPrefix());
            isTokenized = true;
        }

        if (!isTokenized) {
            throw new ParseException(FilterOrderCommand.MESSAGE_NOT_FILTERED);
        }

        return new FilterOrderCommand(additionalRequestPredicate, orderStatusPredicate, priceRangePredicate);
    }
}
