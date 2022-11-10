package seedu.address.model.order.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.order.AdditionalRequests;
import seedu.address.model.order.Order;
import seedu.address.model.order.Price;
import seedu.address.model.order.PriceRange;
import seedu.address.model.order.Request;
import seedu.address.model.pet.Age;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.ColorPattern;
import seedu.address.model.pet.Species;
import seedu.address.testutil.TypicalBuyers;

public class PriceRangePredicateTest {
    @Test
    public void equals() {
        Price firstLowerBound = new Price(7.6);
        Price firstUpperBound = new Price(75.5);
        Price secondLowerBound = new Price(14.3);
        Price secondUpperBound = new Price(333.2);

        PriceRangePredicate firstPredicate = new PriceRangePredicate(firstLowerBound, firstUpperBound);
        PriceRangePredicate secondPredicate = new PriceRangePredicate(secondLowerBound, secondUpperBound);

        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PriceRangePredicate firstPredicateCopy = new PriceRangePredicate(firstLowerBound, firstUpperBound);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_priceEqualsBounds_returnsTrue() {
        Price lowerBound = new Price(7.6);
        Price upperBound = new Price(75.5);
        PriceRangePredicate predicate = new PriceRangePredicate(lowerBound, upperBound);

        PriceRange priceRange = new PriceRange(lowerBound, upperBound);
        Request request = new Request(new Age(1), new Color("white"), new ColorPattern("stripes"),
                new Species("cat"));
        AdditionalRequests additionalRequests = new AdditionalRequests("fluffy fat");
        LocalDate localDate = LocalDate.parse("2020-10-10");
        Price settledPrice = new Price(56);
        Order order = new Order(TypicalBuyers.ALICE, new PriceRange(lowerBound, upperBound), request,
                additionalRequests, localDate, settledPrice);

        assertTrue(predicate.test(order));
    }

    @Test
    public void test_priceWithinBounds_returnsTrue() {
        Price lowerBound = new Price(7.6);
        Price upperBound = new Price(75.5);
        PriceRangePredicate predicate = new PriceRangePredicate(lowerBound, upperBound);

        PriceRange priceRange = new PriceRange(new Price(10.1), new Price(34.4));
        Request request = new Request(new Age(1), new Color("white"), new ColorPattern("stripes"),
                new Species("cat"));
        AdditionalRequests additionalRequests = new AdditionalRequests("fluffy fat");
        LocalDate localDate = LocalDate.parse("2020-10-10");
        Price settledPrice = new Price(7.6);
        Order order = new Order(TypicalBuyers.ALICE, priceRange, request, additionalRequests, localDate, settledPrice);

        assertTrue(predicate.test(order));
    }

    @Test
    public void test_priceOutOfLowerBounds_returnsTrue() {
        Price lowerBound = new Price(7.6);
        Price upperBound = new Price(75.5);
        PriceRangePredicate predicate = new PriceRangePredicate(lowerBound, upperBound);

        PriceRange priceRange = new PriceRange(new Price(1), new Price(34.4));
        Request request = new Request(new Age(1), new Color("white"), new ColorPattern("stripes"),
                new Species("cat"));
        AdditionalRequests additionalRequests = new AdditionalRequests("fluffy fat");
        LocalDate localDate = LocalDate.parse("2020-10-10");
        Price settledPrice = new Price(7.6);
        Order order = new Order(TypicalBuyers.ALICE, priceRange, request, additionalRequests, localDate, settledPrice);

        assertFalse(predicate.test(order));
    }

    @Test
    public void test_priceOutOfUpperBounds_returnsTrue() {
        Price lowerBound = new Price(7.6);
        Price upperBound = new Price(75.5);
        PriceRangePredicate predicate = new PriceRangePredicate(lowerBound, upperBound);

        PriceRange priceRange = new PriceRange(new Price(19.2), new Price(188.2));
        Request request = new Request(new Age(1), new Color("white"), new ColorPattern("stripes"),
                new Species("cat"));
        AdditionalRequests additionalRequests = new AdditionalRequests("fluffy fat");
        LocalDate localDate = LocalDate.parse("2020-10-10");
        Price settledPrice = new Price(7.6);
        Order order = new Order(TypicalBuyers.ALICE, priceRange, request, additionalRequests, localDate, settledPrice);

        assertFalse(predicate.test(order));
    }

}
