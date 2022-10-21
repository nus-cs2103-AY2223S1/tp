package seedu.address.model.order.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.order.AdditionalRequests;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderStatus;
import seedu.address.model.order.Price;
import seedu.address.model.order.PriceRange;
import seedu.address.model.order.Request;
import seedu.address.model.pet.Age;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.ColorPattern;
import seedu.address.model.pet.Species;
import seedu.address.testutil.TypicalBuyers;

public class OrderStatusPredicateTest {
    @Test
    public void equals() {
        OrderStatusPredicate firstPredicate = new OrderStatusPredicate(OrderStatus.PENDING);
        OrderStatusPredicate secondPredicate = new OrderStatusPredicate(OrderStatus.DELIVERING);

        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        OrderStatusPredicate firstPredicateCopy = new OrderStatusPredicate(OrderStatus.PENDING);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_orderStatusEquals_returnsTrue() {
        OrderStatusPredicate predicate = new OrderStatusPredicate(OrderStatus.PENDING);

        Price lowerBound = new Price(7.6);
        Price upperBound = new Price(75.5);
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);
        Request request = new Request(new Age(1), new Color("white"), new ColorPattern("stripes"),
                new Species("cat"));
        AdditionalRequests additionalRequests = new AdditionalRequests("fluffy fat");
        LocalDate localDate = LocalDate.parse("2020-10-10");
        Price settledPrice = new Price(56);
        Order order = new Order(TypicalBuyers.ALICE, priceRange, request, additionalRequests, localDate, settledPrice);

        assertTrue(predicate.test(order));
    }

    @Test
    public void test_orderStatusNotEquals_returnsFalse() {
        OrderStatusPredicate predicate = new OrderStatusPredicate(OrderStatus.DELIVERING);

        Price lowerBound = new Price(7.6);
        Price upperBound = new Price(75.5);
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);
        Request request = new Request(new Age(1), new Color("white"), new ColorPattern("stripes"),
                new Species("cat"));
        AdditionalRequests additionalRequests = new AdditionalRequests("fluffy fat");
        LocalDate localDate = LocalDate.parse("2020-10-10");
        Price settledPrice = new Price(56);
        Order order = new Order(TypicalBuyers.ALICE, priceRange, request, additionalRequests, localDate, settledPrice);

        assertFalse(predicate.test(order));
    }
}
