package seedu.address.logic.parser.filtercommandparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_ADDITIONAL_REQUESTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.filtercommands.FilterOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
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
    public void parse_validArgs_returnsFilterOrderCommand() {
        String input = PREFIX_ORDER_ADDITIONAL_REQUESTS + "fluffy "
                + PREFIX_ORDER_STATUS + "Delivering "
                + PREFIX_ORDER_PRICE_RANGE + "10.1-59.4";
        String inputWithSpaces = "\n" + PREFIX_ORDER_ADDITIONAL_REQUESTS + "fluffy \t"
                + PREFIX_ORDER_STATUS + "Delivering \n "
                + PREFIX_ORDER_PRICE_RANGE + "10.1-59.4 \n";
        FilterOrderCommand expectedCommand = new FilterOrderCommand(
                new AdditionalRequestPredicate<>(Arrays.asList("fluffy")),
                new OrderStatusPredicate<>(OrderStatus.DELIVERING),
                new PriceRangePredicate<>(new Price(10.1), new Price(59.4)));
        try {
            assertEquals(parser.parse(input), expectedCommand);
            assertEquals(parser.parse(inputWithSpaces), expectedCommand);
        } catch (ParseException e) {
            assert false;
        }
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

        String input = PREFIX_ORDER_ADDITIONAL_REQUESTS + "fluffy";
        String inputWithSpaces = "\n" + PREFIX_ORDER_ADDITIONAL_REQUESTS + "\n fluffy \t \n";
        FilterOrderCommand expectedCommand = new FilterOrderCommand(
                new AdditionalRequestPredicate<>(Arrays.asList("fluffy")), defaultPredicate, defaultPredicate);
        try {
            assertEquals(parser.parse(input), expectedCommand);
            assertEquals(parser.parse(inputWithSpaces), expectedCommand);
        } catch (ParseException e) {
            assert false;
        }
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

        String input = PREFIX_ORDER_STATUS + "/Delivering";
        String inputWithSpaces = "\n \t " + PREFIX_ORDER_STATUS + "Delivering \n \n";
        FilterOrderCommand expectedCommand = new FilterOrderCommand(defaultPredicate,
                new OrderStatusPredicate<>(OrderStatus.DELIVERING), defaultPredicate);
        try {
            assertEquals(parser.parse(input), expectedCommand);
            assertEquals(parser.parse(inputWithSpaces), expectedCommand);
        } catch (ParseException e) {
            assert false;
        }
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

        String input = PREFIX_ORDER_PRICE_RANGE + "10.1-59.4";
        String inputWithSpaces = "\n \t \n " + PREFIX_ORDER_PRICE_RANGE + "" + "\t 10.1-59.4 \n";
        FilterOrderCommand expectedCommand = new FilterOrderCommand(defaultPredicate, defaultPredicate,
                new PriceRangePredicate<>(new Price(10.1), new Price(59.4)));
        try {
            assertEquals(parser.parse(input), expectedCommand);
            assertEquals(parser.parse(inputWithSpaces), expectedCommand);
        } catch (ParseException e) {
            assert false;
        }
    }
}
