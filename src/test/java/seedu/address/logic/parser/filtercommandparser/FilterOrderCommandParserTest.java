package seedu.address.logic.parser.filtercommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.filtercommandparser.FilterOrderCommandParser.ADDITIONAL_REQUEST_PREFIX;
import static seedu.address.logic.parser.filtercommandparser.FilterOrderCommandParser.ORDER_STATUS_PREFIX;
import static seedu.address.logic.parser.filtercommandparser.FilterOrderCommandParser.PRICE_RANGE_PREFIX;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.filtercommands.FilterOrderCommand;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderStatus;
import seedu.address.model.order.Price;
import seedu.address.model.order.predicates.AdditionalRequestPredicate;
import seedu.address.model.order.predicates.OrderStatusPredicate;
import seedu.address.model.order.predicates.PriceRangePredicate;

public class FilterOrderCommandParserTest {
    private FilterOrderCommandParser parser = new FilterOrderCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterOrderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        assertParseFailure(parser, "ft/invalid", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterOrderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterOrderCommand() {
        String input = ADDITIONAL_REQUEST_PREFIX + "/fluffy "
                + ORDER_STATUS_PREFIX + "/Delivering "
                + PRICE_RANGE_PREFIX + "/10.1-59.4";
        String inputWithSpaces = "\n" + ADDITIONAL_REQUEST_PREFIX + "/fluffy \t"
                + ORDER_STATUS_PREFIX + "/Delivering \n "
                + PRICE_RANGE_PREFIX + "/10.1-59.4 \n";
        FilterOrderCommand expectedCommand = new FilterOrderCommand(
                new AdditionalRequestPredicate<>(Arrays.asList("fluffy")),
                new OrderStatusPredicate<>(OrderStatus.DELIVERING),
                new PriceRangePredicate<>(new Price(10.1), new Price(59.4)));
        assertParseSuccess(parser, input, expectedCommand);
        assertParseSuccess(parser, inputWithSpaces, expectedCommand);
    }

    @Test
    public void parse_validAdditionalRequest_returnsFilterOrderCommand() {
        Predicate<Order> defaultPredicate = new Predicate<Order>() {
            @Override
            public boolean test(Order order) {
                return true;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };

        String input = ADDITIONAL_REQUEST_PREFIX + "/fluffy";
        String inputWithSpaces = "\n" + ADDITIONAL_REQUEST_PREFIX + "/fluffy \t \n";
        FilterOrderCommand expectedCommand = new FilterOrderCommand(
                new AdditionalRequestPredicate<>(Arrays.asList("fluffy")), defaultPredicate, defaultPredicate);
        assertParseSuccess(parser, input, expectedCommand);
        assertParseSuccess(parser, inputWithSpaces, expectedCommand);
    }

    @Test
    public void parse_validOrderStatus_returnsFilterOrderCommand() {
        Predicate<Order> defaultPredicate = new Predicate<Order>() {
            @Override
            public boolean test(Order order) {
                return true;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };

        String input = ORDER_STATUS_PREFIX + "/Delivering";
        String inputWithSpaces = "\n \t " + ORDER_STATUS_PREFIX + "/Delivering \n \n";
        FilterOrderCommand expectedCommand = new FilterOrderCommand(defaultPredicate,
                new OrderStatusPredicate<>(OrderStatus.DELIVERING), defaultPredicate);
        assertParseSuccess(parser, input, expectedCommand);
        assertParseSuccess(parser, inputWithSpaces, expectedCommand);
    }

    @Test
    public void parse_validPriceRange_returnsFilterOrderCommand() {
        Predicate<Order> defaultPredicate = new Predicate<Order>() {
            @Override
            public boolean test(Order order) {
                return true;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };

        String input = PRICE_RANGE_PREFIX + "/10.1-59.4";
        String inputWithSpaces = "\n \t \n " + PRICE_RANGE_PREFIX + "/10.1-59.4 \n";
        FilterOrderCommand expectedCommand = new FilterOrderCommand(defaultPredicate, defaultPredicate,
                new PriceRangePredicate<>(new Price(10.1), new Price(59.4)));
        assertParseSuccess(parser, input, expectedCommand);
        assertParseSuccess(parser, inputWithSpaces, expectedCommand);
    }
}
