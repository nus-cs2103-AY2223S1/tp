package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderStatus;
import seedu.address.model.order.Price;
import seedu.address.model.order.predicates.AdditionalRequestPredicate;
import seedu.address.model.order.predicates.OrderStatusPredicate;
import seedu.address.model.order.predicates.PriceRangePredicate;
import seedu.address.testutil.TypicalOrders;

public class FilterOrderCommandTest {
    private Model oModel = new ModelManager(TypicalOrders.getTypicalOrdersAddressBook(), new UserPrefs());
    private Model oExpectedModel = new ModelManager(TypicalOrders.getTypicalOrdersAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FilterOrderCommand firstCommand = new FilterOrderCommand(
                new AdditionalRequestPredicate<>(Arrays.asList("white")),
                new OrderStatusPredicate<>(OrderStatus.DELIVERING),
                new PriceRangePredicate<>(new Price(56.4), new Price(190.33)));
        FilterOrderCommand secondCommand = new FilterOrderCommand(
                new AdditionalRequestPredicate<>(Arrays.asList("black")),
                new OrderStatusPredicate<>(OrderStatus.PENDING),
                new PriceRangePredicate<>(new Price(75.4), new Price(150.93)));

        assertTrue(firstCommand.equals(firstCommand));

        FilterOrderCommand firstCommandCopy = new FilterOrderCommand(
                new AdditionalRequestPredicate<>(Arrays.asList("white")),
                new OrderStatusPredicate<>(OrderStatus.DELIVERING),
                new PriceRangePredicate<>(new Price(56.4), new Price(190.33)));

        assertFalse(firstCommand.equals(1));

        assertFalse(firstCommand.equals(null));

        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void equals_differentAdditionalRequest_returnsFalse() {
        FilterOrderCommand firstCommand = new FilterOrderCommand(
                new AdditionalRequestPredicate<>(Arrays.asList("white")),
                new OrderStatusPredicate<>(OrderStatus.DELIVERING),
                new PriceRangePredicate<>(new Price(56.4), new Price(190.33)));
        FilterOrderCommand secondCommand = new FilterOrderCommand(
                new AdditionalRequestPredicate<>(Arrays.asList("black")),
                new OrderStatusPredicate<>(OrderStatus.DELIVERING),
                new PriceRangePredicate<>(new Price(56.4), new Price(190.33)));

        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void equals_differentOrderStatus_returnsFalse() {
        FilterOrderCommand firstCommand = new FilterOrderCommand(
                new AdditionalRequestPredicate<>(Arrays.asList("white")),
                new OrderStatusPredicate<>(OrderStatus.DELIVERING),
                new PriceRangePredicate<>(new Price(56.4), new Price(190.33)));
        FilterOrderCommand secondCommand = new FilterOrderCommand(
                new AdditionalRequestPredicate<>(Arrays.asList("white")),
                new OrderStatusPredicate<>(OrderStatus.PENDING),
                new PriceRangePredicate<>(new Price(56.4), new Price(190.33)));

        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void equals_differentPriceRange_returnsFalse() {
        FilterOrderCommand firstCommand = new FilterOrderCommand(
                new AdditionalRequestPredicate<>(Arrays.asList("white")),
                new OrderStatusPredicate<>(OrderStatus.DELIVERING),
                new PriceRangePredicate<>(new Price(56.4), new Price(190.33)));
        FilterOrderCommand secondCommand = new FilterOrderCommand(
                new AdditionalRequestPredicate<>(Arrays.asList("white")),
                new OrderStatusPredicate<>(OrderStatus.DELIVERING),
                new PriceRangePredicate<>(new Price(77.4), new Price(190.33)));

        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void generatePredicate_noMatchingKeywords_noOrdersFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);

        AdditionalRequestPredicate<Order> additionalRequestPredicate = new AdditionalRequestPredicate<>(
                Arrays.asList("laxative"));
        OrderStatusPredicate<Order> orderOrderStatusPredicate = new OrderStatusPredicate<>(OrderStatus.NEGOTIATING);
        PriceRangePredicate<Order> priceRangePredicate = new PriceRangePredicate<>(
                new Price(100.55), new Price(102.88));

        FilterOrderCommand command = new FilterOrderCommand(additionalRequestPredicate, orderOrderStatusPredicate,
                priceRangePredicate);
        oExpectedModel.updateFilteredOrderList(command.generatePredicate());
        assertCommandSuccess(command, oModel, expectedMessage, oExpectedModel);
        assertEquals(Collections.emptyList(), oModel.getFilteredOrderList());
    }

    @Test
    public void generatePredicate_matchingAdditionalRequest_oneOrderFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);

        AdditionalRequestPredicate<Order> additionalRequestPredicate = new AdditionalRequestPredicate<>(
                Arrays.asList("fluffy"));
        Predicate<Order> defaultPredicate = new Predicate<Order>() {
            @Override
            public boolean test(Order order) {
                return true;
            }
        };

        FilterOrderCommand command = new FilterOrderCommand(additionalRequestPredicate, defaultPredicate,
                defaultPredicate);
        oExpectedModel.updateFilteredOrderList(command.generatePredicate());
        assertCommandSuccess(command, oModel, expectedMessage, oExpectedModel);
        assertEquals(Arrays.asList(TypicalOrders.ORDER_1), oModel.getFilteredOrderList());
    }

    @Test
    public void generatePredicate_matchingOrderStatus_twoOrdersFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);

        OrderStatusPredicate<Order> orderStatusPredicate = new OrderStatusPredicate<>(OrderStatus.PENDING);
        Predicate<Order> defaultPredicate = new Predicate<Order>() {
            @Override
            public boolean test(Order order) {
                return true;
            }
        };

        FilterOrderCommand command = new FilterOrderCommand(defaultPredicate, orderStatusPredicate, defaultPredicate);
        oExpectedModel.updateFilteredOrderList(command.generatePredicate());
        assertCommandSuccess(command, oModel, expectedMessage, oExpectedModel);
        assertEquals(Arrays.asList(TypicalOrders.ORDER_1, TypicalOrders.ORDER_2), oModel.getFilteredOrderList());
    }

    @Test
    public void generatePredicate_exactMatchingPriceRange_oneOrderFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);

        PriceRangePredicate<Order> priceRangePredicate = new PriceRangePredicate<>(new Price(60.6), new Price(150.4));
        Predicate<Order> defaultPredicate = new Predicate<Order>() {
            @Override
            public boolean test(Order order) {
                return true;
            }
        };

        FilterOrderCommand command = new FilterOrderCommand(defaultPredicate, defaultPredicate, priceRangePredicate);
        oExpectedModel.updateFilteredOrderList(command.generatePredicate());
        assertCommandSuccess(command, oModel, expectedMessage, oExpectedModel);
        assertEquals(Arrays.asList(TypicalOrders.ORDER_1), oModel.getFilteredOrderList());
    }

    @Test
    public void generatePredicate_widePriceRange_twoOrdersFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);

        PriceRangePredicate<Order> priceRangePredicate = new PriceRangePredicate<>(new Price(1), new Price(1500.4));
        Predicate<Order> defaultPredicate = new Predicate<Order>() {
            @Override
            public boolean test(Order order) {
                return true;
            }
        };

        FilterOrderCommand command = new FilterOrderCommand(defaultPredicate, defaultPredicate, priceRangePredicate);
        oExpectedModel.updateFilteredOrderList(command.generatePredicate());
        assertCommandSuccess(command, oModel, expectedMessage, oExpectedModel);
        assertEquals(Arrays.asList(TypicalOrders.ORDER_1, TypicalOrders.ORDER_2), oModel.getFilteredOrderList());
    }
}
